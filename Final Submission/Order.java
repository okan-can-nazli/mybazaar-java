import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class Order {
    private Date orderDate;
    private double totalCost;
    private ArrayList<Item> purchasedItems;
    private int customerID;

    public Order(Date orderDate, double totalCost,
                 ArrayList<Item> purchasedItems, int customerID) {
        this.orderDate = orderDate;
        this.totalCost = totalCost;
        this.purchasedItems = new ArrayList<>(purchasedItems);
        this.customerID = customerID;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        return "Order date: " + sdf.format(orderDate) +
                " Customer ID: " + customerID +
                " Total Cost: " + totalCost +
                " Number of purchased items: " + purchasedItems.size();
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public int getCustomerID() {
        return customerID;
    }
}