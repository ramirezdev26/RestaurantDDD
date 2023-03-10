package org.example.business.menu;

import org.example.business.commons.EventsRepository;
import org.example.business.commons.UseCaseForCommand;
import org.example.domain.menu.Menu;
import org.example.domain.menu.commands.CreateMenuCommand;
import org.example.domain.menu.values.Date;
import org.example.domain.menu.values.MenuId;
import org.example.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateMenuUseCase implements UseCaseForCommand<CreateMenuCommand> {

    private final EventsRepository eventsRepository;

    public CreateMenuUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(CreateMenuCommand command) {
        Menu menu = new Menu(MenuId.of(command.getMenuId()), new Date(command.getDate()));
        return menu.getUncommittedChanges().stream().map(event -> eventsRepository.saveEvent(event)).toList();
    }
}
