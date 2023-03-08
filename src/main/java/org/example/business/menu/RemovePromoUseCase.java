package org.example.business.menu;

import org.example.business.commons.EventsRepository;
import org.example.business.commons.UseCaseForCommand;
import org.example.domain.menu.Menu;
import org.example.domain.menu.commands.RemoveItemCommand;
import org.example.domain.menu.commands.RemovePromoCommand;
import org.example.domain.menu.events.DefaultPricesApplied;
import org.example.domain.menu.values.MenuId;
import org.example.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RemovePromoUseCase implements UseCaseForCommand<RemovePromoCommand> {

    private final EventsRepository eventsRepository;
    private ApplyDefaultPricesEventUseCase applyDefaultPricesEventUseCase;

    public RemovePromoUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(RemovePromoCommand command) {

        List<DomainEvent> menuEvents = eventsRepository.findByAggregatedRootId(command.getMenuId());
        Menu menu = Menu.from(MenuId.of(command.getMenuId()), menuEvents);

        menu.removePromo(command.getPromoId());

        return menu.getUncommittedChanges().stream().map(event -> eventsRepository.saveEvent(event)).toList();
    }
}
