package org.example.business.order;

import org.example.business.commons.EventsRepository;
import org.example.domain.order.commands.AddItemToOrderCommand;
import org.example.domain.order.commands.CreateOrderCommand;
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
class CreateOrderUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;
    private CreateOrderUseCase createOrderUseCase;

    @BeforeEach
    void setup(){
        createOrderUseCase = new CreateOrderUseCase(eventsRepository);
    }

    @Test
    void successfulScenario() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // Create Order
        CreateOrderCommand createOrderCommand = new CreateOrderCommand( "orderId", "10-02-2023", "clientId");

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(OrderCreated.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList2 = createOrderUseCase.apply(createOrderCommand);

        Assertions.assertEquals(1,domainEventList2.size());
        Assertions.assertEquals("orderId",domainEventList2.get(0).aggregateRootId());
        Assertions.assertEquals("10-02-2023", domainEventList2.get(0).getClass().getMethod("getDate").invoke(domainEventList2.get(0)));
    }

}