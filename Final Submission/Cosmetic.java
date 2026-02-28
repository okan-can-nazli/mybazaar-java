abstract class Cosmetic extends Item {
    protected String manufacturer;
    protected String brand;
    protected boolean isOrganic;
    protected int expirationYear;
    protected int weight;

    public Cosmetic(double price, String manufacturer, String brand,
                    boolean isOrganic, int expirationYear, int weight) {
        super(price);
        this.manufacturer = manufacturer;
        this.brand = brand;
        this.isOrganic = isOrganic;
        this.expirationYear = expirationYear;
        this.weight = weight;
    }

    protected String getBaseInfo() {
        String info = "Item ID: " + itemID + "\n";
        info += "Price: " + price + " $\n";
        info += "Manufacturer: " + manufacturer + "\n";
        info += "Brand: " + brand + "\n";
        info += "Organic: " + (isOrganic ? "Yes" : "No") + "\n";
        info += "Expiration Date: " + expirationYear + "\n";
        info += "Weight: " + weight + " g.\n";
        return info;
    }
}

class Perfume extends Cosmetic {
    private int lastingDuration;

    public Perfume(double price, String manufacturer, String brand,
                   boolean isOrganic, int expirationYear, int weight,
                   int lastingDuration) {
        super(price, manufacturer, brand, isOrganic, expirationYear, weight);
        this.lastingDuration = lastingDuration;
    }

    @Override
    public String displayInfo() {
        String info = "Type: Perfume\n";
        info += getBaseInfo();
        info += "Lasting Duration: " + lastingDuration;
        return info;
    }
}

class HairCare extends Cosmetic {
    private boolean isMedicated;

    public HairCare(double price, String manufacturer, String brand,
                    boolean isOrganic, int expirationYear, int weight,
                    boolean isMedicated) {
        super(price, manufacturer, brand, isOrganic, expirationYear, weight);
        this.isMedicated = isMedicated;
    }

    @Override
    public String displayInfo() {
        String info = "Type: HairCare\n";
        info += getBaseInfo();
        info += "Medicated: " + (isMedicated ? "Yes" : "No");
        return info;
    }
}

class SkinCare extends Cosmetic {
    private boolean isBabySensitive;

    public SkinCare(double price, String manufacturer, String brand,
                    boolean isOrganic, int expirationYear, int weight,
                    boolean isBabySensitive) {
        super(price, manufacturer, brand, isOrganic, expirationYear, weight);
        this.isBabySensitive = isBabySensitive;
    }

    @Override
    public String displayInfo() {
        String info = "Type: SkinCare\n";
        info += getBaseInfo();
        info += "Baby Sensitive: " + (isBabySensitive ? "Yes" : "No");
        return info;
    }
}