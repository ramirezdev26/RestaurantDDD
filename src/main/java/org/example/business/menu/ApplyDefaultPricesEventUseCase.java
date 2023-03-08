package org.example.business.menu;

import org.example.business.commons.EventsRepository;
import org.example.business.commons.UseCaseForEvent;
import org.example.domain.menu.Menu;
import org.example.domain.menu.events.DefaultPricesApplied;
import org.example.domain.menu.values.MenuId;
import org.example.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplyDefaultPricesEventUseCase implements UseCaseForEvent<DefaultPricesApplied> {

    private final EventsRepository eventsRepository;

    public ApplyDefaultPricesEventUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(DefaultPricesApplied event) {
        List<DomainEvent> menuEvents = eventsRepository.findByAggregatedRootId(event.getMenuId());
        Menu menu = Menu.from(MenuId.of(event.getMenuId()), menuEvents);
        menu.applyDefaultPrices(event.getMenuId(), event.getPromoId());
        return menu.getUncommittedChanges().stream().map(newEvent -> eventsRepository.saveEvent(newEvent)).toList();
    }

}
