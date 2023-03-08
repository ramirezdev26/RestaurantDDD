package org.example.domain.client;

import org.example.domain.client.values.*;
import org.example.generic.Entity;

public class Data extends Entity<DataId> {

    private Phone phone;
    private Name name;

    public Data(DataId id, Phone phone, Name name) {
        super(id);
        this.phone = phone;
        this.name = name;
    }


    public Phone phone() {
        return phone;
    }

    public Name name() {
        return name;
    }


}
