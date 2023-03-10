package org.example.business.order;

import org.example.business.commons.EventsRepository;
import org.example.domain.order.commands.AddItemToOrderCommand;
import org.example.domain.order.events.ItemAddedToOrder;
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
class AddItemToOrderUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;
    private AddItemToOrderUseCase addItemToOrderUseCase;

    @BeforeEach
    void setup(){
        addItemToOrderUseCase = new AddItemToOrderUseCase(eventsRepository);
    }

    @Test
    void successfulScenario() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // Create Order event
        OrderCreated orderCreated = new OrderCreated("10-05-2022");
        orderCreated.setAggregateRootId("orderId");


        // Adding the Item
        AddItemToOrderCommand addItemToOrderCommand = new AddItemToOrderCommand( "itemId", "burger", "Hawaiian Burger", 20000, 2, "orderId");
        Mockito.when(eventsRepository.findByAggregatedRootId(addItemToOrderCommand.getOrderId()))
                .thenAnswer(invocationOnMock ->  {
                    List<DomainEvent> eventList = new ArrayList<DomainEvent>();
                    eventList.add(orderCreated);
                    return eventList;
                });

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(DomainEvent.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList2 = addItemToOrderUseCase.apply(addItemToOrderCommand);

        Assertions.assertEquals(1,domainEventList2.size());
        Assertions.assertEquals("orderId",domainEventList2.get(0).aggregateRootId());
        Assertions.assertEquals(20000, domainEventList2.get(0).getClass().getMethod("getPrice").invoke(domainEventList2.get(0)));
    }

}