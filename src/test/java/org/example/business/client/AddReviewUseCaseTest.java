package org.example.business.client;

import org.example.business.commons.EventsRepository;
import org.example.domain.client.commands.AddReviewCommand;
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
class AddReviewUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;
    private AddReviewUseCase addReviewUseCase;

    @BeforeEach
    void setup(){
        addReviewUseCase = new AddReviewUseCase(eventsRepository);
    }

    @Test
    void successfulScenario() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // Create Client event
        ClientCreated clientCreated = new ClientCreated("dataId", "clientName", "clientPhone");
        clientCreated.setAggregateRootId("clientId");


        // Adding the Review
        AddReviewCommand addReviewCommand = new AddReviewCommand( "reviewId", "10-10-2023", "good", 4, "clientId");
        Mockito.when(eventsRepository.findByAggregatedRootId(addReviewCommand.getClientId()))
                .thenAnswer(invocationOnMock ->  {
                    List<DomainEvent> eventList = new ArrayList<DomainEvent>();
                    eventList.add(clientCreated);
                    return eventList;
                });

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(ReviewAdded.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList2 = addReviewUseCase.apply(addReviewCommand);

        Assertions.assertEquals(1,domainEventList2.size());
        Assertions.assertEquals("clientId",domainEventList2.get(0).aggregateRootId());
        Assertions.assertEquals(4, domainEventList2.get(0).getClass().getMethod("getScore").invoke(domainEventList2.get(0)));
    }

}