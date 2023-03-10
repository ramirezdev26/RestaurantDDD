package org.example.business.menu;

import org.example.business.client.AddReviewUseCase;
import org.example.business.commons.EventsRepository;
import org.example.domain.client.commands.AddReviewCommand;
import org.example.domain.client.events.ClientCreated;
import org.example.domain.client.events.ReviewAdded;
import org.example.domain.menu.commands.AddItemCommand;
import org.example.domain.menu.events.ItemAdded;
import org.example.domain.menu.events.MenuCreated;
import org.example.domain.order.events.ItemAddedToOrder;
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
class AddItemUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;
    private AddItemUseCase addItemUseCase;

    @BeforeEach
    void setup(){
        addItemUseCase = new AddItemUseCase(eventsRepository);

    }


    @Test
    void successfulScenario() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // Create Menu event
        MenuCreated menuCreated = new MenuCreated("10-05-2022");
        menuCreated.setAggregateRootId("menuId");


        // Adding the Item
        AddItemCommand addItemCommand = new AddItemCommand( "itemId", "burger", "a burger with pineapple and bred", "Hawaiian Burger", 20000, "menuId");
        Mockito.when(eventsRepository.findByAggregatedRootId(addItemCommand.getMenuId()))
                .thenAnswer(invocationOnMock ->  {
                    List<DomainEvent> eventList = new ArrayList<DomainEvent>();
                    eventList.add(menuCreated);
                    return eventList;
                });

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(ItemAdded.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList2 = addItemUseCase.apply(addItemCommand);

        Assertions.assertEquals(1,domainEventList2.size());
        Assertions.assertEquals("menuId",domainEventList2.get(0).aggregateRootId());
        Assertions.assertEquals(20000, domainEventList2.get(0).getClass().getMethod("getPrice").invoke(domainEventList2.get(0)));
    }

    @Test
    void idAlreadyExistOnTheList() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // Create Menu event
        MenuCreated menuCreated = new MenuCreated("10-05-2022");
        menuCreated.setAggregateRootId("menuId");

        //Add Item
        ItemAdded item2Added = new ItemAdded("item2Id", "bear", "a bear with candy", "BBC", 8000);
        item2Added.setAggregateRootId("menuId");

        // Adding the Item
        AddItemCommand addItemCommand = new AddItemCommand( "item2Id", "burger", "a burger with pineapple and bred", "Hawaiian Burger", 20000, "menuId");
        Mockito.when(eventsRepository.findByAggregatedRootId(addItemCommand.getMenuId()))
                .thenAnswer(invocationOnMock ->  {
                    List<DomainEvent> eventList = new ArrayList<DomainEvent>();
                    eventList.add(menuCreated);
                    eventList.add(item2Added);
                    return eventList;
                });


        IllegalArgumentException exception = assertThrows( IllegalArgumentException.class, () -> addItemUseCase.apply(addItemCommand));

        Assertions.assertEquals("Id already exists on the Item List",exception.getMessage());

    }


}