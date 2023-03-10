package org.example.business.order;

import org.example.business.commons.EventsRepository;
import org.example.business.commons.UseCaseForCommand;
import org.example.domain.order.values.Date;
import org.example.domain.order.Order;
import org.example.domain.order.commands.CreateOrderCommand;
import org.example.domain.order.values.OrderId;
import org.example.generic.DomainEvent;

import java.util.List;


public class CreateOrderUseCase implements UseCaseForCommand<CreateOrderCommand> {

    private final EventsRepository eventsRepository;

    public CreateOrderUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(CreateOrderCommand command) {
        Order order = new Order(OrderId.of(command.getOrderId()), new Date(command.getDate()));
        return order.getUncommittedChanges().stream().map(event -> eventsRepository.saveEvent(event)).toList();
    }
}
