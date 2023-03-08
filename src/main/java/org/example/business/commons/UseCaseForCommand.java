package org.example.business.commons;

import org.example.generic.Command;
import org.example.generic.DomainEvent;

import java.util.List;

public interface UseCaseForCommand <T extends Command>{

    List<DomainEvent> apply(T command);

}
