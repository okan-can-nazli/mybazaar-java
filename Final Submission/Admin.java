import java.util.Date;
import java.util.ArrayList;

public class Admin extends Employee {
    private String password;

    public Admin(String name, String email, Date dateOfBirth, double salary, String password) {
        super(name, email, dateOfBirth, salary);
        this.password = password;
    }

    @Override
    public void displayPersonalInfo() {
        System.out.println("----------- Admin info -----------");
        System.out.println("Admin name: " + name);
        System.out.println("Admin e-mail: " + email);
        System.out.println("Admin date of birth: " + formatDate(dateOfBirth));
    }

    private String formatDate(Date date) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(date);
    }

    public Customer addCustomer(String name, String email, Date dateOfBirth,
                                double balance, String password, int customerID) {
        return new Customer(name, email, dateOfBirth, balance, password, customerID);
    }

    public Admin addAdmin(String name, String email, Date dateOfBirth,
                          double salary, String password) {
        return new Admin(name, email, dateOfBirth, salary, password);
    }

    public Technician addTechnician(String name, String email, Date dateOfBirth,
                                    double salary, boolean isSenior) {
        return new Technician(name, email, dateOfBirth, salary, isSenior);
    }

    public void displayAllCustomers(ArrayList<Customer> customers) {
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    public void displayCustomer(Customer customer) {
        System.out.println(customer);
    }

    public Campaign launchCampaign(Date startDate, Date endDate, String itemType, int discountRate) {
        if (discountRate > Campaign.MAX_DISCOUNT_RATE) {
            System.out.println("Campaign was not created. Discount rate exceeds maximum rate of " +
                    Campaign.MAX_DISCOUNT_RATE + "%.");
            return null;
        }
        return new Campaign(startDate, endDate, itemType, discountRate);
    }

    public String getPassword() {
        return password;
    }
}