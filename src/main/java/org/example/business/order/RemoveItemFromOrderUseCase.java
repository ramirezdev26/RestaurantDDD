package org.example.business.order;

import org.example.business.commons.EventsRepository;
import org.example.business.commons.UseCaseForCommand;
import org.example.domain.order.Order;
import org.example.domain.order.OrderItem;
import org.example.domain.order.commands.RemoveItemFromOrderCommand;
import org.example.domain.order.values.OrderId;
import org.example.domain.order.values.OrderItemId;
import org.example.generic.DomainEvent;

import java.util.List;
import java.util.Set;

public class RemoveItemFromOrderUseCase implements UseCaseForCommand<RemoveItemFromOrderCommand> {

private final EventsRepository eventsRepository;

public RemoveItemFromOrderUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
        }

@Override
public List<DomainEvent> apply(RemoveItemFromOrderCommand command) {

        List<DomainEvent> orderEvents = eventsRepository.findByAggregatedRootId(command.getOrderId());
        Order order = Order.from(OrderId.of(command.getOrderId()), orderEvents);

        boolean removed = false;
        for ( OrderItem orderItem : order.getItemList() ){
                if( orderItem.identity().value().equals(command.getItemId()) ){
                        order.removeItemFromList(OrderItemId.of(command.getItemId()));
                        removed = true;
                        break;
                }
        }
        if(!removed) { throw new IllegalArgumentException("Id doesn't exists in the Item List"); }

        return order.getUncommittedChanges().stream().map(eventsRepository::saveEvent).toList();
    }
}
