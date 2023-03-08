package org.example.business.menu;

import org.example.business.commons.EventsRepository;
import org.example.business.commons.UseCaseForCommand;
import org.example.domain.menu.Menu;
import org.example.domain.menu.commands.AddPromoCommand;
import org.example.domain.menu.events.PromoApplied;
import org.example.domain.menu.values.MenuId;
import org.example.domain.menu.values.PromoId;
import org.example.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddPromoUseCase implements UseCaseForCommand<AddPromoCommand> {

    private final EventsRepository eventsRepository;

    public AddPromoUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }


    @Override
    public List<DomainEvent> apply(AddPromoCommand command) {

        // Get Menu
        List<DomainEvent> menuEvents = eventsRepository.findByAggregatedRootId(command.getMenuId());
        Menu menu = Menu.from(MenuId.of(command.getMenuId()), menuEvents);

        // Initialized of PromoAppliedEventUseCase and performing that event Use Case


        // Adding Promo to menu
        menu.addAPromo(PromoId.of(command.getPromoId()), command.getQuantityOff(), command.getItemIdList());
        return menu.getUncommittedChanges().stream().map(event -> eventsRepository.saveEvent(event)).toList();
    }
}
