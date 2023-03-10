package org.example.business.client;

import org.example.business.commons.EventsRepository;
import org.example.domain.client.commands.AddAccountCommand;
import org.example.domain.client.events.AccountAdded;
import org.example.domain.client.events.ClientCreated;
import org.example.domain.client.events.ReviewAdded;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AddAccountUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;
    private AddAccountUseCase addAccountUseCase;

    @BeforeEach
    void setup(){
        addAccountUseCase = new AddAccountUseCase(eventsRepository);
    }

    @Test
    void successfulScenario() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // Create Client event
        ClientCreated clientCreated = new ClientCreated("dataId", "clientName", "clientPhone");
        clientCreated.setAggregateRootId("clientId");


        // Adding the Account
        AddAccountCommand addAccountCommand = new AddAccountCommand( "reviewId", "email@account.com", "password", "username", "clientId");
        Mockito.when(eventsRepository.findByAggregatedRootId(addAccountCommand.getClientId()))
                .thenAnswer(invocationOnMock ->  {
                    List<DomainEvent> eventList = new ArrayList<DomainEvent>();
                    eventList.add(clientCreated);
                    return eventList;
                });

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(AccountAdded.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList2 = addAccountUseCase.apply(addAccountCommand);

        Assertions.assertEquals(1,domainEventList2.size());
        Assertions.assertEquals("clientId",domainEventList2.get(0).aggregateRootId());
        Assertions.assertEquals("password", domainEventList2.get(0).getClass().getMethod("getPassword").invoke(domainEventList2.get(0)));
    }

}