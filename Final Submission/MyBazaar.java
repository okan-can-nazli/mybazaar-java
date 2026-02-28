import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyBazaar {
    private ArrayList<Admin> admins;
    private ArrayList<Technician> technicians;
    private ArrayList<Customer> customers;
    private ArrayList<Item> items;
    private ArrayList<Campaign> campaigns;
    private ArrayList<Order> allOrders;
    private int nextCustomerID;

    public MyBazaar() {
        admins = new ArrayList<>();
        technicians = new ArrayList<>();
        customers = new ArrayList<>();
        items = new ArrayList<>();
        campaigns = new ArrayList<>();
        allOrders = new ArrayList<>();
        nextCustomerID = 1;
    }

    public void loadUsers(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\t");
            String type = parts[0];

            if (type.equals("ADMIN")) {
                Admin admin = new Admin(parts[1], parts[2], sdf.parse(parts[3]),
                        Double.parseDouble(parts[4]), parts[5]);
                admins.add(admin);
            } else if (type.equals("TECH")) {
                Technician tech = new Technician(parts[1], parts[2], sdf.parse(parts[3]),
                        Double.parseDouble(parts[4]),
                        parts[5].equals("1"));
                technicians.add(tech);
            } else if (type.equals("CUSTOMER")) {
                Customer customer = new Customer(parts[1], parts[2], sdf.parse(parts[3]),
                        Double.parseDouble(parts[4]), parts[5],
                        nextCustomerID++);
                customers.add(customer);
            }
        }
        br.close();
    }

    public void loadItems(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\t");
            String type = parts[0];
            Item item = null;

            switch(type) {
                case "DESKTOP":
                    item = new Desktop(Double.parseDouble(parts[1]), parts[2], parts[3],
                            Integer.parseInt(parts[4]), Integer.parseInt(parts[5]),
                            parts[6], parts[7], Integer.parseInt(parts[8]),
                            Integer.parseInt(parts[9]), parts[10]);
                    break;
                case "LAPTOP":
                    item = new Laptop(Double.parseDouble(parts[1]), parts[2], parts[3],
                            Integer.parseInt(parts[4]), Integer.parseInt(parts[5]),
                            parts[6], parts[7], Integer.parseInt(parts[8]),
                            Integer.parseInt(parts[9]), parts[10].equals("1"));
                    break;
                case "TABLET":
                    item = new Tablet(Double.parseDouble(parts[1]), parts[2], parts[3],
                            Integer.parseInt(parts[4]), Integer.parseInt(parts[5]),
                            parts[6], parts[7], Integer.parseInt(parts[8]),
                            Integer.parseInt(parts[9]), parts[10]);
                    break;
                case "TV":
                    item = new TV(Double.parseDouble(parts[1]), parts[2], parts[3],
                            Integer.parseInt(parts[4]), Integer.parseInt(parts[5]),
                            Integer.parseInt(parts[6]));
                    break;
                case "SMARTPHONE":
                    item = new SmartPhone(Double.parseDouble(parts[1]), parts[2], parts[3],
                            Integer.parseInt(parts[4]), Integer.parseInt(parts[5]),
                            parts[6]);
                    break;
                case "BOOK":
                    item = new Book(Double.parseDouble(parts[1]), Integer.parseInt(parts[2]),
                            parts[3], parts[4], parts[5], Integer.parseInt(parts[6]));
                    break;
                case "CDDVD":
                    item = new CDDVD(Double.parseDouble(parts[1]), Integer.parseInt(parts[2]),
                            parts[3], parts[4], parts[5]);
                    break;
                case "HAIRCARE":
                    item = new HairCare(Double.parseDouble(parts[1]), parts[2], parts[3],
                            parts[4].equals("1"), Integer.parseInt(parts[5]),
                            Integer.parseInt(parts[6]), parts[7].equals("1"));
                    break;
                case "SKINCARE":
                    item = new SkinCare(Double.parseDouble(parts[1]), parts[2], parts[3],
                            parts[4].equals("1"), Integer.parseInt(parts[5]),
                            Integer.parseInt(parts[6]), parts[7].equals("1"));
                    break;
                case "PERFUME":
                    item = new Perfume(Double.parseDouble(parts[1]), parts[2], parts[3],
                            parts[4].equals("1"), Integer.parseInt(parts[5]),
                            Integer.parseInt(parts[6]), Integer.parseInt(parts[7]));
                    break;
            }

            if (item != null) {
                items.add(item);
            }
        }
        br.close();
    }

    public void processCommands(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = br.readLine()) != null) {
            processCommand(line);
        }
        br.close();
    }

    private void processCommand(String command) {
        String[] parts = command.split("\t");
        String action = parts[0];

        try {
            switch(action) {
                case "ADDCUSTOMER":
                    handleAddCustomer(parts);
                    break;
                case "SHOWCUSTOMER":
                    handleShowCustomer(parts);
                    break;
                case "SHOWCUSTOMERS":
                    handleShowCustomers(parts);
                    break;
                case "SHOWADMININFO":
                    handleShowAdminInfo(parts);
                    break;
                case "CREATECAMPAIGN":
                    handleCreateCampaign(parts);
                    break;
                case "ADDADMIN":
                    handleAddAdmin(parts);
                    break;
                case "ADDTECH":
                    handleAddTech(parts);
                    break;
                case "LISTITEM":
                    handleListItem(parts);
                    break;
                case "SHOWITEMSLOWONSTOCK":
                    handleShowItemsLowOnStock(parts);
                    break;
                case "SHOWVIP":
                    handleShowVIP(parts);
                    break;
                case "DISPITEMSOF":
                    handleDispItemsOf(parts);
                    break;
                case "ADDITEM":
                    handleAddItem(parts);
                    break;
                case "SHOWORDERS":
                    handleShowOrders(parts);
                    break;
                case "CHPASS":
                    handleChangePassword(parts);
                    break;
                case "DEPOSITMONEY":
                    handleDepositMoney(parts);
                    break;
                case "SHOWCAMPAIGNS":
                    handleShowCampaigns(parts);
                    break;
                case "ADDTOCART":
                    handleAddToCart(parts);
                    break;
                case "EMPTYCART":
                    handleEmptyCart(parts);
                    break;
                case "ORDER":
                    handleOrder(parts);
                    break;
                default:
                    System.out.println("Unknown command: " + action);
            }
        } catch (Exception e) {
            System.out.println("Error processing command: " + e.getMessage());
        }
    }

    private void handleAddCustomer(String[] parts) {
        String adminName = parts[1];
        Admin admin = findAdmin(adminName);

        if (admin == null) {
            System.out.println("No admin person named " + adminName + " exists!");
            return;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Customer customer = admin.addCustomer(parts[2], parts[3],
                    sdf.parse(parts[4]),
                    Double.parseDouble(parts[5]),
                    parts[6], nextCustomerID++);
            customers.add(customer);
        } catch (Exception e) {
            System.out.println("Error adding customer: " + e.getMessage());
        }
    }

    private void handleShowCustomer(String[] parts) {
        String adminName = parts[1];
        Admin admin = findAdmin(adminName);

        if (admin == null) {
            System.out.println("No admin person named " + adminName + " exists!");
            return;
        }

        int customerID = Integer.parseInt(parts[2]);
        Customer customer = findCustomer(customerID);

        if (customer == null) {
            System.out.println("No customer with ID number " + customerID + " exists!");
            return;
        }

        admin.displayCustomer(customer);
    }

    private void handleShowCustomers(String[] parts) {
        String adminName = parts[1];
        Admin admin = findAdmin(adminName);

        if (admin == null) {
            System.out.println("No admin person named " + adminName + " exists!");
            return;
        }

        admin.displayAllCustomers(customers);
    }

    private void handleShowAdminInfo(String[] parts) {
        String adminName = parts[1];
        Admin admin = findAdmin(adminName);

        if (admin == null) {
            System.out.println("No admin person named " + adminName + " exists!");
            return;
        }

        admin.displayPersonalInfo();
    }

    private void handleCreateCampaign(String[] parts) {
        String adminName = parts[1];
        Admin admin = findAdmin(adminName);

        if (admin == null) {
            System.out.println("No admin person named " + adminName + " exists!");
            return;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date startDate = sdf.parse(parts[2]);
            Date endDate = sdf.parse(parts[3]);
            String itemType = parts[4];
            int rate = Integer.parseInt(parts[5]);

            for (Campaign c : campaigns) {
                if (c.getItemType().equalsIgnoreCase(itemType)) {
                    System.out.println("A campaign already exists for " + itemType);
                    return;
                }
            }

            Campaign campaign = admin.launchCampaign(startDate, endDate, itemType, rate);
            if (campaign != null) {
                campaigns.add(campaign);
            }
        } catch (Exception e) {
            System.out.println("Error creating campaign: " + e.getMessage());
        }
    }

    private void handleAddAdmin(String[] parts) {
        String adminName = parts[1];
        Admin admin = findAdmin(adminName);

        if (admin == null) {
            System.out.println("No admin person named " + adminName + " exists!");
            return;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Admin newAdmin = admin.addAdmin(parts[2], parts[3], sdf.parse(parts[4]),
                    Double.parseDouble(parts[5]), parts[6]);
            admins.add(newAdmin);
        } catch (Exception e) {
            System.out.println("Error adding admin: " + e.getMessage());
        }
    }

    private void handleAddTech(String[] parts) {
        String adminName = parts[1];
        Admin admin = findAdmin(adminName);

        if (admin == null) {
            System.out.println("No admin person named " + adminName + " exists!");
            return;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Technician tech = admin.addTechnician(parts[2], parts[3], sdf.parse(parts[4]),
                    Double.parseDouble(parts[5]),
                    parts[6].equals("1"));
            technicians.add(tech);
        } catch (Exception e) {
            System.out.println("Error adding technician: " + e.getMessage());
        }
    }

    private void handleListItem(String[] parts) {
        String name = parts[1];
        Employee employee = findEmployee(name);

        if (employee == null) {
            System.out.println("No admin or technician person named " + name + " exists!");
            return;
        }

        employee.listAvailableItems(items);
    }

    private void handleShowItemsLowOnStock(String[] parts) {
        String name = parts[1];
        Employee employee = findEmployee(name);

        if (employee == null) {
            System.out.println("No admin or technician person named " + name + " exists!");
            return;
        }

        if (parts.length > 2) {
            int maxStock = Integer.parseInt(parts[2]);
            employee.displayStockAmount(items, maxStock);
        } else {
            employee.displayStockAmount(items);
        }
    }

    private void handleShowVIP(String[] parts) {
        String name = parts[1];
        Employee employee = findEmployee(name);

        if (employee == null) {
            System.out.println("No admin or technician person named " + name + " exists!");
            return;
        }

        employee.displayVIPCustomers(customers);
    }

    private void handleDispItemsOf(String[] parts) {
        String techName = parts[1];
        Technician tech = findTechnician(techName);

        if (tech == null) {
            System.out.println("No technician person named " + techName + " exists!");
            return;
        }

        String[] types = parts[2].split(":");
        tech.displayItemsOfTypes(items, types);
    }

    private void handleAddItem(String[] parts) {
        String techName = parts[1];
        Technician tech = findTechnician(techName);

        if (tech == null) {
            System.out.println("No technician person named " + techName + " exists!");
            return;
        }

        String[] itemData = parts[2].split(":");
        String type = itemData[0];
        String[] args = Arrays.copyOfRange(itemData, 1, itemData.length);

        Item item = tech.addItem(type, args);
        if (item != null) {
            items.add(item);
        }
    }

    private void handleShowOrders(String[] parts) {
        String techName = parts[1];
        Technician tech = findTechnician(techName);

        if (tech == null) {
            System.out.println("No technician person named " + techName + " exists!");
            return;
        }

        tech.displayOrders(allOrders);
    }

    private void handleChangePassword(String[] parts) {
        int customerID = Integer.parseInt(parts[1]);
        Customer customer = findCustomer(customerID);

        if (customer == null) {
            System.out.println("No customer with ID number " + customerID + " exists!");
            return;
        }

        customer.changePassword(parts[2], parts[3]);
    }

    private void handleDepositMoney(String[] parts) {
        int customerID = Integer.parseInt(parts[1]);
        Customer customer = findCustomer(customerID);

        if (customer == null) {
            System.out.println("No customer with ID number " + customerID + " exists!");
            return;
        }

        double amount = Double.parseDouble(parts[2]);
        customer.updateBalance(amount);
    }

    private void handleShowCampaigns(String[] parts) {
        int customerID = Integer.parseInt(parts[1]);
        Customer customer = findCustomer(customerID);

        if (customer == null) {
            System.out.println("No customer with ID number " + customerID + " exists!");
            return;
        }

        customer.viewActiveCampaigns(campaigns);
    }

    private void handleAddToCart(String[] parts) {
        int customerID = Integer.parseInt(parts[1]);
        Customer customer = findCustomer(customerID);

        if (customer == null) {
            System.out.println("No customer with ID number " + customerID + " exists!");
            return;
        }

        int itemID = Integer.parseInt(parts[2]);
        Item item = findItem(itemID);
        customer.addToCart(item);
    }

    private void handleEmptyCart(String[] parts) {
        int customerID = Integer.parseInt(parts[1]);
        Customer customer = findCustomer(customerID);

        if (customer == null) {
            System.out.println("No customer with ID number " + customerID + " exists!");
            return;
        }

        customer.clearCart();
    }

    // *** FIXED METHOD - THIS IS THE ONLY CHANGE ***
    private void handleOrder(String[] parts) {
        int customerID = Integer.parseInt(parts[1]);
        Customer customer = findCustomer(customerID);

        if (customer == null) {
            System.out.println("No customer with ID number " + customerID + " exists!");
            return;
        }

        int orderCountBefore = customer.getOrderHistory().size();
        boolean success = customer.placeOrder(parts[2], campaigns);

        if (success && customer.getOrderHistory().size() > orderCountBefore) {
            // Add only the newly created order
            allOrders.add(customer.getOrderHistory().get(customer.getOrderHistory().size() - 1));
        }
    }
    // *** END OF FIX ***

    private Admin findAdmin(String name) {
        for (Admin admin : admins) {
            if (admin.getName().equals(name)) {
                return admin;
            }
        }
        return null;
    }

    private Technician findTechnician(String name) {
        for (Technician tech : technicians) {
            if (tech.getName().equals(name)) {
                return tech;
            }
        }
        return null;
    }

    private Employee findEmployee(String name) {
        Employee emp = findAdmin(name);
        if (emp == null) {
            emp = findTechnician(name);
        }
        return emp;
    }

    private Customer findCustomer(int id) {
        for (Customer customer : customers) {
            if (customer.getCustomerID() == id) {
                return customer;
            }
        }
        return null;
    }

    private Item findItem(int id) {
        for (Item item : items) {
            if (item.getItemID() == id) {
                return item;
            }
        }
        return null;
    }
}