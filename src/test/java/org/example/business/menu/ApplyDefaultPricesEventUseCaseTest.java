package org.example.business.menu;

import org.example.business.commons.EventsRepository;
import org.example.domain.menu.Promo;
import org.example.domain.menu.commands.AddPromoCommand;
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
class ApplyDefaultPricesEventUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;
    private ApplyDefaultPricesEventUseCase applyDefaultPricesEventUseCase;

    @BeforeEach
    void setup(){
        applyDefaultPricesEventUseCase = new ApplyDefaultPricesEventUseCase(eventsRepository);
    }

    @Test
    void successfulScenario() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // Create Menu event
        MenuCreated menuCreated = new MenuCreated("10-05-2022");
        menuCreated.setAggregateRootId("menuId");

        // Adding Items to list
        ItemAdded itemAdded = new ItemAdded("itemId", "burger", "a burger with pineapple and bred", "Hawaiian Burger", 20000);
        ItemAdded item2Added = new ItemAdded("item2Id", "bear", "a bear with candy", "BBC", 8000);

        // Adding Promo to Menu
        Set<String> itemIdList = new HashSet<>();
        itemIdList.add("itemId");
        PromoAdded promoAdded = new PromoAdded("promoId", 10, itemIdList);

        // Applying the promo
        PromoApplied promoApplied = new PromoApplied("menuId", 10, itemIdList);


        // Applying default prices
        DefaultPricesApplied defaultPricesApplied = new DefaultPricesApplied( "menuId","promoId");
        Mockito.when(eventsRepository.findByAggregatedRootId(defaultPricesApplied.getMenuId()))
                .thenAnswer(invocationOnMock ->  {
                    List<DomainEvent> eventList = new ArrayList<DomainEvent>();
                    eventList.add(menuCreated);
                    eventList.add(itemAdded);
                    eventList.add(item2Added);
                    eventList.add(promoAdded);
                    eventList.add(promoApplied);
                    return eventList;
                });

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(DefaultPricesApplied.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList2 =  applyDefaultPricesEventUseCase.apply(defaultPricesApplied);

        Assertions.assertEquals(1,domainEventList2.size());
        Assertions.assertEquals("menuId",domainEventList2.get(0).aggregateRootId());
        Assertions.assertEquals("menuId", domainEventList2.get(0).getClass().getMethod("getMenuId").invoke(domainEventList2.get(0)));
        Assertions.assertEquals("promoId", domainEventList2.get(0).getClass().getMethod("getPromoId").invoke(domainEventList2.get(0)));
    }

}