package org.fitsay.tourist_trips.Orders;
import java.util.*;
public class Orders {
    protected  List<Order> orders = new ArrayList<>();
    public void SortByNameCustomer(){
        List<Order> orders2 = orders;
        Collections.sort(orders2, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o1.getNameCustomer().compareTo(o2.getNameCustomer());
            }
        });
    }
    public List<Order> getOrders() {
        return orders;
    }
}
