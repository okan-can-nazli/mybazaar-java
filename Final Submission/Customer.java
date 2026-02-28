import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class Customer extends Person {
    public static final double SILVER_THRESHOLD = 1000.0;
    public static final double GOLDEN_THRESHOLD = 5000.0;
    public static final double SILVER_DISCOUNT = 0.10;
    public static final double GOLDEN_DISCOUNT = 0.15;

    private int customerID;
    private String password;
    private double balance;
    private CustomerStatus status;
    private ArrayList<Item> shoppingCart;
    private ArrayList<Order> orderHistory;
    private double totalSpent;

    public Customer(String name, String email, Date dateOfBirth,
                    double balance, String password, int customerID) {
        super(name, email, dateOfBirth);
        this.customerID = customerID;
        this.password = password;
        this.balance = balance;
        this.status = CustomerStatus.CLASSIC;
        this.shoppingCart = new ArrayList<>();
        this.orderHistory = new ArrayList<>();
        this.totalSpent = 0.0;
    }

    public boolean changePassword(String oldPassword, String newPassword) {
        if (!this.password.equals(oldPassword)) {
            System.out.println("The given password does not match the current password. Please try again.");
            return false;
        }
        this.password = newPassword;
        System.out.println("The password has been successfully changed.");
        return true;
    }

    public void updateBalance(double amount) {
        this.balance += amount;
    }

    public void viewActiveCampaigns(ArrayList<Campaign> campaigns) {
        if (campaigns.isEmpty()) {
            System.out.println("No campaign has been created so far!");
            return;
        }

        System.out.println("Active campaigns:");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (Campaign campaign : campaigns) {
            System.out.println(campaign.getDiscountRate() + "% sale of " +
                    campaign.getItemType() + " until " +
                    sdf.format(campaign.getEndDate()));
        }
    }

    public boolean addToCart(Item item) {
        if (item == null) {
            System.out.println("Invalid item ID");
            return false;
        }

        shoppingCart.add(item);
        System.out.println("The item " + item.getClass().getSimpleName() +
                " has been successfully added to your cart.");
        return true;
    }

    public void clearCart() {
        shoppingCart.clear();
        System.out.println("The cart has been emptied.");
    }

    public boolean placeOrder(String password, ArrayList<Campaign> campaigns) {
        if (!this.password.equals(password)) {
            System.out.println("Order could not be placed. Invalid password.");
            return false;
        }

        if (shoppingCart.isEmpty()) {
            System.out.println("You should add some items to your cart before order request!");
            return false;
        }

        double totalCost = calculateTotalCost(campaigns);

        if (status == CustomerStatus.SILVER) {
            totalCost *= (1 - SILVER_DISCOUNT);
        } else if (status == CustomerStatus.GOLDEN) {
            totalCost *= (1 - GOLDEN_DISCOUNT);
        }

        if (balance < totalCost) {
            System.out.println("Order could not be placed. Insufficient funds.");
            return false;
        }

        balance -= totalCost;
        totalSpent += totalCost;

        Order order = new Order(new Date(), totalCost,
                new ArrayList<>(shoppingCart), customerID);
        orderHistory.add(order);

        CustomerStatus oldStatus = status;
        updateStatus();

        shoppingCart.clear();

        System.out.println("Done! Your order will be delivered as soon as possible. Thank you!");

        if (oldStatus != status) {
            String discountStr = status == CustomerStatus.SILVER ? "10" : "15";
            System.out.println("Congratulations! You have been upgraded to a " +
                    status + " MEMBER! You have earned a discount of " +
                    discountStr + "% on all purchases.");
        } else {
            displayStatusProgress();
        }

        return true;
    }

    private double calculateTotalCost(ArrayList<Campaign> campaigns) {
        double total = 0.0;
        for (Item item : shoppingCart) {
            double itemPrice = item.getPrice();

            String itemType = item.getClass().getSimpleName().toUpperCase();
            for (Campaign campaign : campaigns) {
                if (campaign.getItemType().equalsIgnoreCase(itemType)) {
                    itemPrice *= (1 - campaign.getDiscountRate() / 100.0);
                    break;
                }
            }

            total += itemPrice;
        }
        return total;
    }

    private void updateStatus() {
        if (totalSpent >= GOLDEN_THRESHOLD) {
            status = CustomerStatus.GOLDEN;
        } else if (totalSpent >= SILVER_THRESHOLD) {
            status = CustomerStatus.SILVER;
        }
    }

    private void displayStatusProgress() {
        if (status == CustomerStatus.GOLDEN) {
            return;
        }

        double nextThreshold = (status == CustomerStatus.CLASSIC) ?
                SILVER_THRESHOLD : GOLDEN_THRESHOLD;
        String nextStatus = (status == CustomerStatus.CLASSIC) ?
                "SILVER" : "GOLDEN";
        double remaining = nextThreshold - totalSpent;

        System.out.printf("You need to spend %.2f more TL to become a %s MEMBER.%n",
                remaining, nextStatus);
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        return "Customer name: " + name + " ID: " + customerID +
                " e-mail: " + email + " Date of Birth: " + sdf.format(dateOfBirth) +
                " Status: " + status;
    }

    public int getCustomerID() {
        return customerID;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }
}