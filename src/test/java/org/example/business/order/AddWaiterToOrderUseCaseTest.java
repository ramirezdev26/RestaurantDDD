package org.example.business.order;

import org.example.business.commons.EventsRepository;
import org.example.domain.order.commands.AddItemToOrderCommand;
import org.example.domain.order.commands.AddWaiterCommand;
import org.example.domain.order.events.ItemAddedToOrder;
import org.example.domain.order.events.OrderCreated;
import org.example.domain.order.events.WaiterAdded;
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
class AddWaiterToOrderUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;
    private AddWaiterToOrderUseCase addWaiterToOrderUseCase;

    @BeforeEach
    void setup(){
        addWaiterToOrderUseCase = new AddWaiterToOrderUseCase(eventsRepository);
    }

    @Test
    void successfulScenario() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // Create Order event
        OrderCreated orderCreated = new OrderCreated("10-05-2022");
        orderCreated.setAggregateRootId("orderId");


        // Adding the Waiter
        AddWaiterCommand addWaiterCommand = new AddWaiterCommand( "itemId", "waiter@email.com", "Name", "20000", "1234567981", "username", "orderId");
        Mockito.when(eventsRepository.findByAggregatedRootId(addWaiterCommand.getOrderId()))
                .thenAnswer(invocationOnMock ->  {
                    List<DomainEvent> eventList = new ArrayList<DomainEvent>();
                    eventList.add(orderCreated);
                    return eventList;
                });

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(WaiterAdded.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList2 = addWaiterToOrderUseCase.apply(addWaiterCommand);

        Assertions.assertEquals(1,domainEventList2.size());
        Assertions.assertEquals("orderId",domainEventList2.get(0).aggregateRootId());
        Assertions.assertEquals("waiter@email.com", domainEventList2.get(0).getClass().getMethod("getEmail").invoke(domainEventList2.get(0)));
    }

}