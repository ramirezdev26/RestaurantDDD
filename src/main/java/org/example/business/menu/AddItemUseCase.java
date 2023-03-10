package org.example.business.menu;

import org.example.business.commons.EventsRepository;
import org.example.business.commons.UseCaseForCommand;
import org.example.domain.menu.Item;
import org.example.domain.menu.Menu;
import org.example.domain.menu.commands.AddItemCommand;
import org.example.domain.menu.values.ItemId;
import org.example.domain.menu.values.MenuId;
import org.example.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddItemUseCase implements UseCaseForCommand<AddItemCommand> {

    private final EventsRepository eventsRepository;

    public AddItemUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }


    @Override
    public List<DomainEvent> apply(AddItemCommand command) {

        List<DomainEvent> menuEvents = eventsRepository.findByAggregatedRootId(command.getMenuId());
        Menu menu = Menu.from(MenuId.of(command.getMenuId()), menuEvents);

        for ( Item item : menu.getItemList() ) {
            if (item.identity().value().equals(command.getItemId())) {
                throw new IllegalArgumentException("Id already exists in the Item List");
            }
        }
        menu.addToListOfItems(ItemId.of(command.getItemId()), command.getCategory(), command.getDescription(), command.getName(), command.getPrice());

        return menu.getUncommittedChanges().stream().map(event -> eventsRepository.saveEvent(event)).toList();
    }
}
