package org.example.business.menu;

import org.example.business.commons.EventsRepository;
import org.example.domain.menu.commands.AddItemCommand;
import org.example.domain.menu.commands.AddPromoCommand;
import org.example.domain.menu.events.ItemAdded;
import org.example.domain.menu.events.MenuCreated;
import org.example.domain.menu.events.PromoAdded;
import org.example.domain.menu.events.PromoApplied;
import org.example.generic.DomainEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AddPromoUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;
    private AddPromoUseCase addPromoUseCase;

    @BeforeEach
    void setup(){
        addPromoUseCase = new AddPromoUseCase(eventsRepository);
    }

    @Test
    void successfulScenario() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // Create Menu event
        MenuCreated menuCreated = new MenuCreated("10-05-2022");
        menuCreated.setAggregateRootId("menuId");

        // Adding Items to list
        ItemAdded itemAdded = new ItemAdded("itemId", "burger", "a burger with pineapple and bred", "Hawaiian Burger", 20000);
        ItemAdded item2Added = new ItemAdded("item2Id", "bear", "a bear with candy", "BBC", 8000);


        // Adding the Promo
        Set<String> itemIdList = new HashSet<>();
        itemIdList.add("itemId");
        AddPromoCommand addPromoCommand = new AddPromoCommand( "promoId", 10, itemIdList, "menuId");
        Mockito.when(eventsRepository.findByAggregatedRootId(addPromoCommand.getMenuId()))
                .thenAnswer(invocationOnMock ->  {
                    List<DomainEvent> eventList = new ArrayList<DomainEvent>();
                    eventList.add(menuCreated);
                    eventList.add(itemAdded);
                    eventList.add(item2Added);
                    return eventList;
                });

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(PromoAdded.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList2 = addPromoUseCase.apply(addPromoCommand);

        Assertions.assertEquals(1,domainEventList2.size());
        Assertions.assertEquals("menuId",domainEventList2.get(0).aggregateRootId());
        Assertions.assertEquals(10, domainEventList2.get(0).getClass().getMethod("getQuantityOff").invoke(domainEventList2.get(0)));
    }

}