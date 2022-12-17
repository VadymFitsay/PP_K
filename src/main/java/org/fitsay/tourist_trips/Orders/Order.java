package org.fitsay.tourist_trips.Orders;

import org.fitsay.tourist_trips.Vouchers.TransportType;
import java.sql.Date;
public class Order {
    private int OrderID;
    private String NameCustomer;
    private String PhoneNumber;
    private Date DepartureDate;
    private int OfferID;
    protected int CustomDuration;
    protected TransportType CustomTransport;
    protected double price;
    public Order(int orderID, String nameCustomer, String phoneNumber, Date departureDate, int offerID, int customDuration, TransportType customTransport, double price) {
        OrderID = orderID;
        NameCustomer = nameCustomer;
        PhoneNumber = phoneNumber;
        DepartureDate = departureDate;
        OfferID = offerID;
        CustomDuration = customDuration;
        CustomTransport = customTransport;
        this.price = price;
    }
    @Override
    public String toString() {
        return "ID: "+OrderID+"; Name: "+NameCustomer+"; Price: "+price;
    }
    public int getOrderID() {
        return OrderID;
    }
    public double getPrice() {
        return price;
    }
    public int getOfferID() {
        return OfferID;
    }
    public Date getDepartureDate() {
        return DepartureDate;
    }
    public int getCustomDuration() {
        return CustomDuration;
    }
    public TransportType getCustomTransport() {
        return CustomTransport;
    }
    public String getNameCustomer() {
        return NameCustomer;
    }
    public String getPhoneNumber() {
        return PhoneNumber;
    }
}
