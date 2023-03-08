package org.example.business.client;

import org.example.business.commons.EventsRepository;
import org.example.domain.client.commands.CreateClientCommand;
import org.example.domain.client.events.ClientCreated;
import org.example.generic.DomainEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateClientUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;
    private CreateClientUseCase createClientUseCase;

    @BeforeEach
    void setup(){
        createClientUseCase = new CreateClientUseCase(eventsRepository);
    }

    @Test
    void successfulScenario(){

        CreateClientCommand createClientCommand = new CreateClientCommand("clientId", "dataId","clientName", "clientPhone");
        ClientCreated clientCreated = new ClientCreated("dataId", "clientName", "clientPhone");
        clientCreated.setAggregateRootId("clientId");
        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(ClientCreated.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = createClientUseCase.apply(createClientCommand);

        Assertions.assertEquals(1,domainEventList.size());
        Assertions.assertEquals("clientId",domainEventList.get(0).aggregateRootId());

    }

}