package org.example.domain.order;

import org.example.domain.client.values.ClientId;
import org.example.domain.order.events.*;
import org.example.domain.order.values.*;
import org.example.generic.EventChange;

import java.util.HashSet;

public class OrderChange extends EventChange {

    public OrderChange( Order order ){

        apply( ( OrderCreated event ) -> {
            order.date = new Date(event.getDate());
            order.itemList = new HashSet<>();
            order.state = new State("sent");
            order.total = new Total(0);
        });

        apply( ( WaiterAdded event ) -> {
            order.waiter = new Waiter(WaiterId.of(event.getWaiterId()),
                    new Email(event.getEmail()),
                    new WaiterName(event.getName()),
                    new Password(event.getPassword()),
                    new Phone(event.getPhone()),
                    new Username(event.getUsername()));
        });

        apply( ( StateChanged event ) -> {
            order.state = new State(event.getStatus());
        });

        apply( ( ClientAssociated event ) -> {
            order.clientId = ClientId.of(event.getClientId());
        });

        apply( ( ItemAddedToOrder event ) -> {
            OrderItem newOrderItem = new OrderItem(OrderItemId.of(event.getItemId()),
                    new Category(event.getCategory()),
                    new ItemName(event.getItemName()),
                    new Price(event.getPrice()),
                    new Quantity(event.getQuantity()));
             order.itemList.add(newOrderItem);
        });

        apply( ( ItemRemovedFromOrder event ) ->{
            order.itemList.removeIf(item -> item.identity().value().equals(event.getItemId()));
        });

        apply( ( TotalCalculated event ) -> {
            Integer newTotal = 0;
            for ( OrderItem orderItem : order.itemList ){
                newTotal += orderItem.getPrice().value();
            }
            order.total = new Total(newTotal);
        });

    }

}
