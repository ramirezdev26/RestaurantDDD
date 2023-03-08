package org.example.business.menu;

import org.example.business.commons.EventsRepository;
import org.example.business.commons.UseCaseForCommand;
import org.example.domain.menu.Menu;
import org.example.domain.menu.commands.AddPromoCommand;
import org.example.domain.menu.commands.RemoveItemCommand;
import org.example.domain.menu.values.MenuId;
import org.example.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RemoveItemUseCase implements UseCaseForCommand<RemoveItemCommand> {

    private final EventsRepository eventsRepository;

    public RemoveItemUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(RemoveItemCommand command) {
        List<DomainEvent> menuEvents = eventsRepository.findByAggregatedRootId(command.getMenuId());
        Menu menu = Menu.from(MenuId.of(command.getMenuId()), menuEvents);
        menu.removeFromListOfItems(command.getItemId());
        return menu.getUncommittedChanges().stream().map(event -> eventsRepository.saveEvent(event)).toList();
    }

}
