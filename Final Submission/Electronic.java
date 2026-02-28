abstract class Electronic extends Item {
    protected String manufacturer;
    protected String brand;
    protected int maxVolt;
    protected int maxWatt;

    public Electronic(double price, String manufacturer, String brand,
                      int maxVolt, int maxWatt) {
        super(price);
        this.manufacturer = manufacturer;
        this.brand = brand;
        this.maxVolt = maxVolt;
        this.maxWatt = maxWatt;
    }

    protected String getBaseInfo() {
        String info = "Item ID: " + itemID + "\n";
        info += "Price: " + price + " $\n";
        info += "Manufacturer: " + manufacturer + "\n";
        info += "Brand: " + brand + "\n";
        info += "Max Volt: " + maxVolt + " V.\n";
        info += "Max Watt: " + maxWatt + " W.\n";
        return info;
    }
}

abstract class Computer extends Electronic {
    protected String operatingSystem;
    protected String cpuType;
    protected int ramCapacity;
    protected int hddCapacity;

    public Computer(double price, String manufacturer, String brand,
                    int maxVolt, int maxWatt, String operatingSystem,
                    String cpuType, int ramCapacity, int hddCapacity) {
        super(price, manufacturer, brand, maxVolt, maxWatt);
        this.operatingSystem = operatingSystem;
        this.cpuType = cpuType;
        this.ramCapacity = ramCapacity;
        this.hddCapacity = hddCapacity;
    }

    protected String getComputerInfo() {
        String info = getBaseInfo();
        info += "Operating System: " + operatingSystem + "\n";
        info += "CPU Type: " + cpuType + "\n";
        info += "RAM Capacity: " + ramCapacity + "\n";
        info += "HDD Capacity: " + hddCapacity + "\n";
        return info;
    }
}

class Desktop extends Computer {
    private String boxColor;

    public Desktop(double price, String manufacturer, String brand,
                   int maxVolt, int maxWatt, String operatingSystem,
                   String cpuType, int ramCapacity, int hddCapacity,
                   String boxColor) {
        super(price, manufacturer, brand, maxVolt, maxWatt,
                operatingSystem, cpuType, ramCapacity, hddCapacity);
        this.boxColor = boxColor;
    }

    @Override
    public String displayInfo() {
        String info = "Type: Desktop\n";
        info += getComputerInfo();
        info += "Box Color: " + boxColor;
        return info;
    }
}

class Laptop extends Computer {
    private boolean hasHDMI;

    public Laptop(double price, String manufacturer, String brand,
                  int maxVolt, int maxWatt, String operatingSystem,
                  String cpuType, int ramCapacity, int hddCapacity,
                  boolean hasHDMI) {
        super(price, manufacturer, brand, maxVolt, maxWatt,
                operatingSystem, cpuType, ramCapacity, hddCapacity);
        this.hasHDMI = hasHDMI;
    }

    @Override
    public String displayInfo() {
        String info = "Type: Laptop\n";
        info += getComputerInfo();
        info += "HDMI Support: " + (hasHDMI ? "Yes" : "No");
        return info;
    }
}

class Tablet extends Computer {
    private String dimensions;

    public Tablet(double price, String manufacturer, String brand,
                  int maxVolt, int maxWatt, String operatingSystem,
                  String cpuType, int ramCapacity, int hddCapacity,
                  String dimensions) {
        super(price, manufacturer, brand, maxVolt, maxWatt,
                operatingSystem, cpuType, ramCapacity, hddCapacity);
        this.dimensions = dimensions;
    }

    @Override
    public String displayInfo() {
        String info = "Type: Tablet\n";
        info += getComputerInfo();
        info += "Dimensions: " + dimensions;
        return info;
    }
}

class TV extends Electronic {
    private int screenSize;

    public TV(double price, String manufacturer, String brand,
              int maxVolt, int maxWatt, int screenSize) {
        super(price, manufacturer, brand, maxVolt, maxWatt);
        this.screenSize = screenSize;
    }

    @Override
    public String displayInfo() {
        String info = "Type: TV\n";
        info += getBaseInfo();
        info += "Screen size: " + screenSize + "\"";
        return info;
    }
}

class SmartPhone extends Electronic {
    private String screenType;

    public SmartPhone(double price, String manufacturer, String brand,
                      int maxVolt, int maxWatt, String screenType) {
        super(price, manufacturer, brand, maxVolt, maxWatt);
        this.screenType = screenType;
    }

    @Override
    public String displayInfo() {
        String info = "Type: SmartPhone\n";
        info += getBaseInfo();
        info += "Screen Type: " + screenType;
        return info;
    }
}