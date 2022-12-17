package org.fitsay.tourist_trips.Vouchers;
public enum TransportType {
    Plane(2000),Bus(3000),Train(2000);
    private int price;
    TransportType(int price){
        this.price = price;
    }
    public static TransportType getTypeByInd(int ind) {
        for(TransportType type : TransportType.values()) {
            if(type.ordinal() == ind-1) {
                return type;
            }
        }
        return null;
    }
}
