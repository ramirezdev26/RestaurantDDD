package org.example.business.order;

import org.example.business.commons.EventsRepository;
import org.example.domain.order.commands.AddItemToOrderCommand;
import org.example.domain.order.events.ItemAddedToOrder;
import org.example.domain.order.events.OrderCreated;
import org.example.domain.order.events.TotalCalculated;
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
class CalculateTotalEventUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;
    private CalculateTotalEventUseCase calculateTotalEventUseCase;

    @BeforeEach
    void setup(){
        calculateTotalEventUseCase = new CalculateTotalEventUseCase(eventsRepository);
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


        // Calculating the total
        TotalCalculated totalCalculated = new TotalCalculated("orderId");
        Mockito.when(eventsRepository.findByAggregatedRootId(totalCalculated.getOrderId()))
                .thenAnswer(invocationOnMock ->  {
                    List<DomainEvent> eventList = new ArrayList<DomainEvent>();
                    eventList.add(orderCreated);
                    eventList.add(itemAddedToOrder);
                    eventList.add(itemAddedToOrder2);
                    return eventList;
                });

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(TotalCalculated.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList2 = calculateTotalEventUseCase.apply(totalCalculated);

        Assertions.assertEquals(1,domainEventList2.size());
        Assertions.assertEquals("orderId",domainEventList2.get(0).aggregateRootId());
        Assertions.assertEquals("orderId", domainEventList2.get(0).getClass().getMethod("getOrderId").invoke(domainEventList2.get(0)));
    }

}