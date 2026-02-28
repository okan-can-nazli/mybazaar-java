import java.util.Date;
import java.util.ArrayList;

public class Technician extends Employee {
    private boolean isSenior;

    public Technician(String name, String email, Date dateOfBirth, double salary, boolean isSenior) {
        super(name, email, dateOfBirth, salary);
        this.isSenior = isSenior;
    }

    public void displayOrders(ArrayList<Order> orders) {
        if (!isSenior) {
            System.out.println(name + " is not authorized to display orders!");
            return;
        }

        System.out.println("Order History:");
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    public void displayItemInfo(Item item) {
        System.out.println(item.displayInfo());
    }

    public void displayItemsOfTypes(ArrayList<Item> items, String[] types) {
        for (Item item : items) {
            String itemType = item.getClass().getSimpleName().toUpperCase();
            for (String type : types) {
                if (itemType.equals(type.toUpperCase())) {
                    System.out.println(item.displayInfo());
                    System.out.println("-----------------------");
                    break;
                }
            }
        }
    }

    public Item addItem(String type, String[] args) {
        try {
            switch(type.toUpperCase()) {
                case "LAPTOP":
                    return new Laptop(Double.parseDouble(args[0]), args[1], args[2],
                            Integer.parseInt(args[3]), Integer.parseInt(args[4]),
                            args[5], args[6], Integer.parseInt(args[7]),
                            Integer.parseInt(args[8]), Integer.parseInt(args[9]) == 1);
                case "DESKTOP":
                    return new Desktop(Double.parseDouble(args[0]), args[1], args[2],
                            Integer.parseInt(args[3]), Integer.parseInt(args[4]),
                            args[5], args[6], Integer.parseInt(args[7]),
                            Integer.parseInt(args[8]), args[9]);
                case "TABLET":
                    return new Tablet(Double.parseDouble(args[0]), args[1], args[2],
                            Integer.parseInt(args[3]), Integer.parseInt(args[4]),
                            args[5], args[6], Integer.parseInt(args[7]),
                            Integer.parseInt(args[8]), args[9]);
                case "TV":
                    return new TV(Double.parseDouble(args[0]), args[1], args[2],
                            Integer.parseInt(args[3]), Integer.parseInt(args[4]),
                            Integer.parseInt(args[5]));
                case "SMARTPHONE":
                    return new SmartPhone(Double.parseDouble(args[0]), args[1], args[2],
                            Integer.parseInt(args[3]), Integer.parseInt(args[4]),
                            args[5]);
                case "BOOK":
                    return new Book(Double.parseDouble(args[0]), Integer.parseInt(args[1]),
                            args[2], args[3], args[4], Integer.parseInt(args[5]));
                case "CDDVD":
                    return new CDDVD(Double.parseDouble(args[0]), Integer.parseInt(args[1]),
                            args[2], args[3], args[4]);
                case "HAIRCARE":
                    return new HairCare(Double.parseDouble(args[0]), args[1], args[2],
                            Integer.parseInt(args[3]) == 1, Integer.parseInt(args[4]),
                            Integer.parseInt(args[5]), Integer.parseInt(args[6]) == 1);
                case "SKINCARE":
                    return new SkinCare(Double.parseDouble(args[0]), args[1], args[2],
                            Integer.parseInt(args[3]) == 1, Integer.parseInt(args[4]),
                            Integer.parseInt(args[5]), Integer.parseInt(args[6]) == 1);
                case "PERFUME":
                    return new Perfume(Double.parseDouble(args[0]), args[1], args[2],
                            Integer.parseInt(args[3]) == 1, Integer.parseInt(args[4]),
                            Integer.parseInt(args[5]), Integer.parseInt(args[6]));
                default:
                    System.out.println("No item type " + type + " found");
                    return null;
            }
        } catch (Exception e) {
            System.out.println("Error creating item: " + e.getMessage());
            return null;
        }
    }

    public boolean isSenior() {
        return isSenior;
    }
}