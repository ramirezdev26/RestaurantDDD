package org.example.business.order;

import org.example.business.commons.EventsRepository;
import org.example.business.commons.UseCaseForEvent;
import org.example.domain.client.values.ClientId;
import org.example.domain.order.Order;
import org.example.domain.order.events.ClientAssociated;
import org.example.domain.order.values.OrderId;
import org.example.generic.DomainEvent;

import java.util.List;

public class AssociateClientToOrderEventUseCase implements UseCaseForEvent<ClientAssociated> {

    private final EventsRepository eventsRepository;

    public AssociateClientToOrderEventUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(ClientAssociated event) {

        List<DomainEvent> orderEvents = eventsRepository.findByAggregatedRootId(event.getOrderId());
        Order order = Order.from(OrderId.of(event.getOrderId()), orderEvents);

        order.associateClient(ClientId.of(event.getClientId()), OrderId.of(event.getOrderId()));

        return order.getUncommittedChanges().stream().map(newEvent -> eventsRepository.saveEvent(newEvent)).toList();
    }
}
