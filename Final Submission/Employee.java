import java.util.Date;
import java.util.ArrayList;

public abstract class Employee extends Person {
    protected double salary;

    public Employee(String name, String email, Date dateOfBirth, double salary) {
        super(name, email, dateOfBirth);
        this.salary = salary;
    }

    public void displayStockAmount(ArrayList<Item> items) {
        displayStockAmount(items, Integer.MAX_VALUE);
    }

    public void displayStockAmount(ArrayList<Item> items, int maxStock) {
        int[] stock = new int[10];
        String[] types = {"Book", "CDDVD", "Desktop", "Laptop", "Tablet",
                "TV", "SmartPhone", "HairCare", "Perfume", "SkinCare"};

        for (Item item : items) {
            String type = item.getClass().getSimpleName();
            int index = getTypeIndex(type);
            if (index >= 0) {
                stock[index]++;
            }
        }

        for (int i = 0; i < types.length; i++) {
            if (maxStock == Integer.MAX_VALUE || stock[i] <= maxStock) {
                System.out.println(types[i] + " : " + stock[i]);
            }
        }
    }

    public void listAvailableItems(ArrayList<Item> items) {
        for (Item item : items) {
            System.out.println(item.displayInfo());
            System.out.println("-----------------------");
        }
    }

    public void displayVIPCustomers(ArrayList<Customer> customers) {
        for (Customer c : customers) {
            if (c.getStatus() == CustomerStatus.GOLDEN) {
                System.out.println(c);
            }
        }
    }

    private int getTypeIndex(String type) {
        switch(type) {
            case "Book": return 0;
            case "CDDVD": return 1;
            case "Desktop": return 2;
            case "Laptop": return 3;
            case "Tablet": return 4;
            case "TV": return 5;
            case "SmartPhone": return 6;
            case "HairCare": return 7;
            case "Perfume": return 8;
            case "SkinCare": return 9;
            default: return -1;
        }
    }

    public double getSalary() {
        return salary;
    }
}