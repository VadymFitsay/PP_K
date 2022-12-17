package org.fitsay.tourist_trips.Vouchers;

import java.sql.Date;
public class Voucher {
    protected int ID;
    protected String VoucherName;
    protected VoucherType Type;
    protected String Country;
    protected String Hotel;
    protected Date Date;
    protected TransportType Transport;
    protected int Duration;
    protected double price;
    public Voucher(int ID, String voucherName, VoucherType type, String country, String hotel, Date date, TransportType transport, int duration, double price) {
        this.ID = ID;
        VoucherName = voucherName;
        Type = type;
        Country = country;
        Hotel = hotel;
        Date = date;
        Transport = transport;
        Duration = duration;
        this.price = price;
    }
    public Voucher(Voucher voucher ) {
        this.VoucherName = voucher.VoucherName;
        this.Type = voucher.Type;
        this.Transport = voucher.Transport;
        this.Duration = voucher.Duration;
        this.price = voucher.price;
        this.Hotel = voucher.Hotel;
        this.Date = voucher.Date;
        this.Country = voucher.Country;
    }
    @Override
    public String toString() {
        return "ID: "+ID+"; Name: "+VoucherName+"; Country: "+Country+"; Price: "+price ;
    }
    public String getVoucherName() {
        return VoucherName;
    }
    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getHotel() {
        return Hotel;
    }
    public int getID() {
        return ID;
    }

    public java.sql.Date getDate() {
        return Date;
    }

    public int getDuration() {
        return Duration;
    }

    public double getPrice() {
        return price;
    }
    public VoucherType getType() {
        return Type;
    }
    public TransportType getTransport() {
        return Transport;
    }
}