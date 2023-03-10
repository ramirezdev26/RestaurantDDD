package org.example.business.order;

import org.example.business.commons.EventsRepository;
import org.example.business.commons.UseCaseForEvent;
import org.example.domain.order.Order;
import org.example.domain.order.events.TotalCalculated;
import org.example.domain.order.values.OrderId;
import org.example.generic.DomainEvent;

import java.util.List;

public class CalculateTotalEventUseCase implements UseCaseForEvent<TotalCalculated> {

    private final EventsRepository eventsRepository;

    public CalculateTotalEventUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(TotalCalculated event) {
        List<DomainEvent> orderEvents = eventsRepository.findByAggregatedRootId(event.getOrderId());
        Order order = Order.from(OrderId.of(event.getOrderId()), orderEvents);
        order.calculateTotal(OrderId.of(event.getOrderId()));
        return order.getUncommittedChanges().stream().map(newEvent -> eventsRepository.saveEvent(newEvent)).toList();
    }
}
