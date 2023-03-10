package org.example.business.order;

import org.example.business.commons.EventsRepository;
import org.example.business.commons.UseCaseForCommand;
import org.example.domain.order.Order;
import org.example.domain.order.commands.AddItemToOrderCommand;
import org.example.domain.order.commands.AddWaiterCommand;
import org.example.domain.order.values.OrderId;
import org.example.domain.order.values.OrderItemId;
import org.example.domain.order.values.WaiterId;
import org.example.generic.DomainEvent;

import java.util.List;

public class AddWaiterToOrderUseCase implements UseCaseForCommand<AddWaiterCommand> {

    private final EventsRepository eventsRepository;

    public AddWaiterToOrderUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }


    @Override
    public List<DomainEvent> apply(AddWaiterCommand command) {
        List<DomainEvent> orderEvents = eventsRepository.findByAggregatedRootId(command.getOrderId());
        Order order = Order.from(OrderId.of(command.getOrderId()), orderEvents);
        order.addWaiter(WaiterId.of(command.getWaiterId()), command.getEmail(), command.getName(), command.getPassword(), command.getPhone(), command.getUsername());
        return order.getUncommittedChanges().stream().map(event -> eventsRepository.saveEvent(event)).toList();
    }
}
