package org.example.business.menu;

import org.example.business.commons.EventsRepository;
import org.example.domain.menu.commands.RemoveItemCommand;
import org.example.domain.menu.events.DefaultPricesApplied;
import org.example.domain.menu.events.ItemAdded;
import org.example.domain.menu.events.ItemRemoved;
import org.example.domain.menu.events.MenuCreated;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RemoveItemUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;
    private RemoveItemUseCase removeItemUseCase;

    @BeforeEach
    void setup(){
        removeItemUseCase = new RemoveItemUseCase(eventsRepository);
    }

    @Test
    void successfulScenario() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // Create Menu event
        MenuCreated menuCreated = new MenuCreated("10-05-2022");
        menuCreated.setAggregateRootId("menuId");

        // Adding Items to list
        ItemAdded itemAdded = new ItemAdded("itemId", "burger", "a burger with pineapple and bred", "Hawaiian Burger", 20000);
        ItemAdded item2Added = new ItemAdded("item2Id", "bear", "a bear with candy", "BBC", 8000);

        // Removing an item
        RemoveItemCommand removeItemCommand = new RemoveItemCommand( "itemId","menuId");
        Mockito.when(eventsRepository.findByAggregatedRootId(removeItemCommand.getMenuId()))
                .thenAnswer(invocationOnMock ->  {
                    List<DomainEvent> eventList = new ArrayList<DomainEvent>();
                    eventList.add(menuCreated);
                    eventList.add(itemAdded);
                    eventList.add(item2Added);
                    return eventList;
                });

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(ItemRemoved.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList2 = removeItemUseCase.apply(removeItemCommand);

        Assertions.assertEquals(1,domainEventList2.size());
        Assertions.assertEquals("menuId",domainEventList2.get(0).aggregateRootId());
        Assertions.assertEquals("itemId", domainEventList2.get(0).getClass().getMethod("getItemId").invoke(domainEventList2.get(0)));
    }

    }