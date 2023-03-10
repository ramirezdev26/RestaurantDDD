package org.example.business.order;

import org.example.business.commons.EventsRepository;
import org.example.business.commons.UseCaseForCommand;
import org.example.domain.order.Order;
import org.example.domain.order.OrderItem;
import org.example.domain.order.commands.AddItemToOrderCommand;
import org.example.domain.order.values.OrderId;
import org.example.domain.order.values.OrderItemId;
import org.example.generic.DomainEvent;

import java.util.List;
import java.util.Set;

public class AddItemToOrderUseCase implements UseCaseForCommand<AddItemToOrderCommand> {

    private final EventsRepository eventsRepository;

    public AddItemToOrderUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }


    @Override
    public List<DomainEvent> apply(AddItemToOrderCommand command) {

        List<DomainEvent> orderEvents = eventsRepository.findByAggregatedRootId(command.getOrderId());
        Order order = Order.from(OrderId.of(command.getOrderId()), orderEvents);

        for ( OrderItem orderItem : order.getItemList() ) {
            if (orderItem.identity().value().equals(command.getItemId())) {
                throw new IllegalArgumentException("Id already exists in the Item List");
            }
        }
        order.addItemToList(OrderItemId.of(command.getItemId()), command.getCategory(), command.getItemName(), command.getPrice(), command.getQuantity());

        return order.getUncommittedChanges().stream().map(event -> eventsRepository.saveEvent(event)).toList();
    }
}
