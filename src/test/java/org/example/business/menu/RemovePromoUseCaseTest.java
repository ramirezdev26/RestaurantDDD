package org.example.business.menu;

import org.example.business.commons.EventsRepository;
import org.example.domain.menu.commands.RemovePromoCommand;
import org.example.domain.menu.events.*;
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
class RemovePromoUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;
    private RemovePromoUseCase removePromoUseCase;

    @BeforeEach
    void setup(){
        removePromoUseCase = new RemovePromoUseCase(eventsRepository);
    }

    @Test
    void successfulScenario() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // Create Menu event
        MenuCreated menuCreated = new MenuCreated("10-05-2022");
        menuCreated.setAggregateRootId("menuId");

        // Adding Promo to Menu
        Set<String> itemIdList = new HashSet<>();
        itemIdList.add("itemId");
        PromoAdded promoAdded = new PromoAdded("promoId", 10, itemIdList);

        // Removing Promo from Menu
        RemovePromoCommand removePromoCommand = new RemovePromoCommand( "promoId","menuId");
        Mockito.when(eventsRepository.findByAggregatedRootId(removePromoCommand.getMenuId()))
                .thenAnswer(invocationOnMock ->  {
                    List<DomainEvent> eventList = new ArrayList<DomainEvent>();
                    eventList.add(menuCreated);
                    eventList.add(promoAdded);
                    return eventList;
                });

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(PromoRemoved.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList2 = removePromoUseCase.apply(removePromoCommand);

        Assertions.assertEquals(1,domainEventList2.size());
        Assertions.assertEquals("menuId",domainEventList2.get(0).aggregateRootId());
        Assertions.assertEquals("promoId", domainEventList2.get(0).getClass().getMethod("getPromoId").invoke(domainEventList2.get(0)));

    }

}

