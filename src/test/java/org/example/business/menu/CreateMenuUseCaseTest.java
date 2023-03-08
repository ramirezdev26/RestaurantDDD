package org.example.business.menu;

import org.example.business.client.CreateClientUseCase;
import org.example.business.commons.EventsRepository;
import org.example.domain.client.commands.CreateClientCommand;
import org.example.domain.client.events.ClientCreated;
import org.example.domain.menu.commands.CreateMenuCommand;
import org.example.domain.menu.events.MenuCreated;
import org.example.generic.DomainEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateMenuUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;
    private CreateMenuUseCase createMenuUseCase;

    @BeforeEach
    void setup(){
        createMenuUseCase = new CreateMenuUseCase(eventsRepository);
    }

    @Test
    void successfulScenario() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        CreateMenuCommand createMenuCommand = new CreateMenuCommand("menuId", "10-02-2023");
        MenuCreated menuCreated = new MenuCreated("10-02-2023");
        menuCreated.setAggregateRootId("menuId");
        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(MenuCreated.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = createMenuUseCase.apply(createMenuCommand);

        Assertions.assertEquals(1,domainEventList.size());
        Assertions.assertEquals("menuId",domainEventList.get(0).aggregateRootId());
        Assertions.assertEquals("10-02-2023", domainEventList.get(0).getClass().getMethod("getDate").invoke(domainEventList.get(0)));


    }

}