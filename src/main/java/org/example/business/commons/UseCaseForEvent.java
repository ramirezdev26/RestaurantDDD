package org.example.business.commons;

import org.example.generic.Command;
import org.example.generic.DomainEvent;

import java.util.List;

public interface UseCaseForEvent <T extends DomainEvent>{

    List<DomainEvent> apply(T event);

}
