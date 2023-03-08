package org.example.domain.client.events;

import org.example.generic.DomainEvent;

public class ClientCreated extends DomainEvent {

    protected String dataId;
    protected String name;
    protected String phone;

    public ClientCreated(String dataId, String name, String phone) {
        super("restaurant.client.ClientCreated");
        this.dataId = dataId;
        this.name = name;
        this.phone = phone;
    }

    public String getDataId() { return dataId; }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

}
