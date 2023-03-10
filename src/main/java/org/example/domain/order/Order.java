package org.example.domain.order;

import org.example.domain.client.values.ClientId;
import org.example.domain.order.events.*;
import org.example.domain.order.values.*;
import org.example.generic.AggregateRoot;
import org.example.generic.DomainEvent;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Order extends AggregateRoot<OrderId> {

    protected Waiter waiter;
    protected Set<OrderItem> itemList;

    protected Date date;
    protected State state;
    protected ClientId clientId;
    protected Total total;

    public Order(OrderId id, Date date) {
        super(id);
        subscribe(new OrderChange(this));
        appendChange(new OrderCreated(date.value())).apply();
    }

    private Order(OrderId id) {
        super(id);
        subscribe(new OrderChange(this));
    }

    public static Order from(OrderId orderId, List<DomainEvent> events) {
        var order = new Order(orderId);
        events.forEach(order::applyEvent);
        return order;
    }

    public void addWaiter(WaiterId waiterId, String email, String name, String password, String phone, String username){
        Objects.requireNonNull(waiterId);
        Objects.requireNonNull(email);
        Objects.requireNonNull(name);
        Objects.requireNonNull(password);
        Objects.requireNonNull(phone);
        Objects.requireNonNull(username);
        appendChange(new WaiterAdded(waiterId.value(), email, name, password, phone, username)).apply();
    }


    public void changeState(String state){
        Objects.requireNonNull(state);
        appendChange(new StateChanged(state)).apply();
    }

    public void associateClient(ClientId clientId, OrderId orderId){
        Objects.requireNonNull(clientId);
        appendChange(new ClientAssociated(clientId.value(), orderId.value())).apply();
    }

    public void addItemToList(OrderItemId itemId, String category, String itemName, Integer price, Integer quantity){
        Objects.requireNonNull(itemId);
        Objects.requireNonNull(category);
        Objects.requireNonNull(itemName);
        Objects.requireNonNull(price);
        Objects.requireNonNull(quantity);
        appendChange(new ItemAddedToOrder(itemId.value(), category, itemName, price, quantity)).apply();
    }

    public void calculateTotal(OrderId orderId){
        Objects.requireNonNull(orderId);
        appendChange(new TotalCalculated(orderId.value())).apply();
    }

    public void removeItemFromList(OrderItemId itemId){
        Objects.requireNonNull(itemId);
        appendChange(new ItemRemovedFromOrder(itemId.value())).apply();
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public Set<OrderItem> getItemList() {
        return itemList;
    }

    public Date getDate() {
        return date;
    }

    public State getState() {
        return state;
    }

    public ClientId getClientId() {
        return clientId;
    }

    public Total getTotal() {
        return total;
    }
}
