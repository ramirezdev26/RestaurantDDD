package org.example.business.order;

import org.example.business.commons.EventsRepository;
import org.example.business.commons.UseCaseForCommand;
import org.example.domain.order.Order;
import org.example.domain.order.commands.ChangeStateCommand;
import org.example.domain.order.values.OrderId;
import org.example.generic.DomainEvent;

import java.util.List;

public class ChangeStateUseCase implements UseCaseForCommand<ChangeStateCommand> {

private final EventsRepository eventsRepository;

public ChangeStateUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
}

@Override
public List<DomainEvent> apply(ChangeStateCommand command) {
        List<DomainEvent> orderEvents = eventsRepository.findByAggregatedRootId(command.getOrderId());
        Order order = Order.from(OrderId.of(command.getOrderId()), orderEvents);
        order.changeState(command.getStatus());
        return order.getUncommittedChanges().stream().map(event -> eventsRepository.saveEvent(event)).toList();
    }
}
