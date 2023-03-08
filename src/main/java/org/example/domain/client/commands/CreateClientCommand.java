package org.example.domain.client.commands;

import org.example.generic.Command;

public class CreateClientCommand extends Command {

    private String clientId;
    private String dataId;
    private String name;
    private String phone;
    public CreateClientCommand(){}

    public CreateClientCommand(String clientId, String dataId, String name, String phone) {
        this.clientId = clientId;
        this.dataId = dataId;
        this.name = name;
        this.phone = phone;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getDataId() { return dataId; }

    public void setDataId(String dataId) { this.dataId = dataId; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
