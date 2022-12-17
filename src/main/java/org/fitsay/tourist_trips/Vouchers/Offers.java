package org.fitsay.tourist_trips.Vouchers;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
public class Offers {
    protected List<Voucher> vouchers = new ArrayList<>();
    public void SortByCountry(){
        Collections.sort(vouchers, new Comparator<Voucher>() {
            public int compare(Voucher o1, Voucher o2) {
                return o1.getCountry().compareTo(o2.getCountry());
            }
        });
    }
    public void SortByName(){
        Collections.sort(vouchers, new Comparator<Voucher>() {
            public int compare(Voucher o1, Voucher o2) {
                return o1.getVoucherName().compareTo(o2.VoucherName);
            }
        });
    }
    public void SortByCost(){
        Collections.sort(vouchers, new Comparator<Voucher>() {
            public int compare(Voucher o1, Voucher o2) {
                return Double.compare(o1.price,o2.price);
            }
        });
    }
    public List<Voucher> getVouchers() {
        return vouchers;
    }
}
