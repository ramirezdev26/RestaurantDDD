package org.example.business.order;

import org.example.business.commons.EventsRepository;
import org.example.domain.order.commands.AddItemToOrderCommand;
import org.example.domain.order.commands.RemoveItemFromOrderCommand;
import org.example.domain.order.events.ItemAddedToOrder;
import org.example.domain.order.events.ItemRemovedFromOrder;
import org.example.domain.order.events.OrderCreated;
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
class RemoveItemFromOrderUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;
    private RemoveItemFromOrderUseCase removeItemFromOrderUseCase;

    @BeforeEach
    void setup(){
        removeItemFromOrderUseCase = new RemoveItemFromOrderUseCase(eventsRepository);
    }

    @Test
    void successfulScenario() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // Create Order event
        OrderCreated orderCreated = new OrderCreated("10-05-2022");
        orderCreated.setAggregateRootId("orderId");

        // Adding the Items
        ItemAddedToOrder itemAddedToOrder = new ItemAddedToOrder( "itemId", "burger", "Hawaiian Burger", 20000, 2);
        itemAddedToOrder.setAggregateRootId("orderId");
        ItemAddedToOrder itemAddedToOrder2 = new ItemAddedToOrder( "itemId2", "drink", "Bold Bear", 8000, 6);
        itemAddedToOrder2.setAggregateRootId("orderId");


        // Removing the Item
        RemoveItemFromOrderCommand removeItemFromOrderCommand = new RemoveItemFromOrderCommand( "itemId", "orderId");
        Mockito.when(eventsRepository.findByAggregatedRootId(removeItemFromOrderCommand.getOrderId()))
                .thenAnswer(invocationOnMock ->  {
                    List<DomainEvent> eventList = new ArrayList<DomainEvent>();
                    eventList.add(orderCreated);
                    eventList.add(itemAddedToOrder);
                    eventList.add(itemAddedToOrder2);
                    return eventList;
                });

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(ItemRemovedFromOrder.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList2 = removeItemFromOrderUseCase.apply(removeItemFromOrderCommand);

        Assertions.assertEquals(1,domainEventList2.size());
        Assertions.assertEquals("orderId",domainEventList2.get(0).aggregateRootId());
        Assertions.assertEquals("itemId", domainEventList2.get(0).getClass().getMethod("getItemId").invoke(domainEventList2.get(0)));
    }

}