import java.util.ArrayList;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class Library {

    private ArrayList<Material> materials;
    public static final String FILE_NAME = "library.txt";

    public Library() {
        this.materials = new ArrayList<>();
        loadMaterialsFromFile(); //load saved materials to program
    }

    // Add any material polymorphically
    public void addMaterial(Material newMaterial) {
        if (findMaterial(newMaterial.getId()) != null) {
            System.out.println("Error: A material with same ID " + newMaterial.getId() + " already exists in the library!");
            return;
        }

        materials.add(newMaterial);
        System.out.println("New " + newMaterial.getMaterialType() + " successfully added to library!");
        saveToFile();
    }


    public void listAllMaterials() {
        if (materials.isEmpty()) {
            System.out.println("There are  no any materials in the library :(");
        } else { //print all materials info
            for (int i = 0; i < materials.size(); i++) {
                System.out.println((i + 1) + ". " + materials.get(i).getDetails());
            }
        }
    }

    // list materials by type using instanceof ***
    public void listByType(String type) {
        int counter = 0;

        for (Material material : materials) {
            boolean matches = false;

            if (type.equalsIgnoreCase("Book") && material instanceof Book) { //is material a Book obj?
                matches = true;
            } else if (type.equalsIgnoreCase("Magazine") && material instanceof Magazine) { // is material a Magazine obj
                matches = true;
            } else if (type.equalsIgnoreCase("DVD") && material instanceof DVD) { // is material a DVD obj
                matches = true;
            }

            if (matches) {
                counter++;
                System.out.println(counter + ". " + material.getDetails());
            }
        }

        if (counter == 0) {
            System.out.println("No " + type + "s found in the library.");
        }
    }

    // Find material by ID
    public Material findMaterial(String id) {
        for (Material material : materials) {
            if (id.equals(material.getId())) {
                return material;
            }
        }
        return null;
    }

    // Borrow material (only if Borrowable)
    public void borrowMaterial(String id) {
        Material material = findMaterial(id);

        if (material == null) {
            System.out.println("Material with ID " + id + " not found.");
            return;
        }

        // Check if material is Borrowable using instanceof
        if (material instanceof Borrowable) {
            Borrowable borrowable = (Borrowable) material;  // Type casting

            if (borrowable.isAvailableToBorrow()) {
                borrowable.borrow();
                saveToFile();
                System.out.println("Successfully borrowed: " + material.getTitle());
            } else {
                System.out.println("Warning: " + material.getTitle() + " is already borrowed :(");
            }
        } else {
            System.out.println("This material type cannot be borrowed (DVDs are for watching only).");
        }
    }

    // Return material (only if Borrowable)
    public void returnMaterial(String id) {
        Material material = findMaterial(id);

        if (material == null) {
            System.out.println("Material with ID " + id + " not found.");
            return;
        }

        if (material instanceof Borrowable) {
            Borrowable borrowable = (Borrowable) material;

            if (!borrowable.isAvailableToBorrow()) {
                borrowable.returnItem();
                saveToFile();
                System.out.println("Successfully returned: " + material.getTitle());
            } else {
                System.out.println("Warning: " + material.getTitle() + " is already available.");
            }
        } else {
            System.out.println("This material type cannot be returned.");
        }
    }

    // Reserve material (only if Reservable)
    public void reserveMaterial(String id, String userName) {
        Material material = findMaterial(id);

        if (material == null) {
            System.out.println("Material with ID " + id + " not found.");
            return;
        }

        // Check if material is Reservable using instanceof
        if (material instanceof Reservable) {
            Reservable reservable = (Reservable) material;
            reservable.reserve(userName);
            saveToFile();
        } else {
            System.out.println("This material type cannot be reserved (only Books can be reserved).");
        }
    }


    public void watchDVD(String id) {
        Material material = findMaterial(id);

        if (material == null) {
            System.out.println("Material with ID " + id + " not found.");
            return;
        }

        if (material instanceof DVD) {
            DVD dvd = (DVD) material;
            dvd.watch();
        } else {
            System.out.println("Only DVDs can be watched!");
        }
    }

    // Save all materials to file
    private void saveToFile() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (Material material : materials) {
                writer.write(material.toFileFormat());
                writer.write("\n");
            }
        } catch (IOException e) {
            System.err.println("Error while writing the file: " + e.getMessage());
        }
    }

    // Load materials from file
    private void loadMaterialsFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No existing library file found. Starting fresh.");
            return;
        }

        try (FileReader reader = new FileReader(FILE_NAME)) {
            StringBuilder fileContent = new StringBuilder();
            int charCode;

            while ((charCode = reader.read()) != -1) {
                fileContent.append((char) charCode);
            }

            String allText = fileContent.toString();
            String[] lines = allText.split("\n");

            for (String line : lines) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");

                try {
                    String type = parts[0].trim();

                    // Load BOOK
                    if (type.equals("Book") && parts.length >= 6) {
                        String isbn = parts[1].trim();
                        String title = parts[2].trim();
                        String author = parts[3].trim();
                        int year = Integer.parseInt(parts[4].trim());
                        boolean borrowed = Boolean.parseBoolean(parts[5].trim());

                        Book book = new Book(isbn, title, author, year);
                        book.setIsBorrowed(borrowed);

                        // Load reservation info if available
                        if (parts.length >= 8) {
                            boolean reserved = Boolean.parseBoolean(parts[6].trim());
                            String holder = parts[7].trim();
                            book.setReserved(reserved);
                            if (!holder.equals("null")) {
                                book.setReservationHolder(holder);
                            }
                        }

                        materials.add(book);

                        // Load MAGAZINE
                    } else if (type.equals("Magazine") && parts.length >= 6) {
                        String id = parts[1].trim();
                        String title = parts[2].trim();
                        int issue = Integer.parseInt(parts[3].trim());
                        int year = Integer.parseInt(parts[4].trim());
                        boolean borrowed = Boolean.parseBoolean(parts[5].trim());

                        Magazine magazine = new Magazine(id, title, issue, year);
                        magazine.setIsBorrowed(borrowed);
                        materials.add(magazine);

                        // Load DVD
                    } else if (type.equals("DVD") && parts.length >= 6) {
                        String id = parts[1].trim();
                        String title = parts[2].trim();
                        String director = parts[3].trim();
                        int duration = Integer.parseInt(parts[4].trim());
                        int year = Integer.parseInt(parts[5].trim());

                        DVD dvd = new DVD(id, title, director, duration, year);
                        materials.add(dvd);
                    }

                } catch (NumberFormatException e) {
                    System.err.println("Skipping invalid line: " + line);
                }
            }

            System.out.println(materials.size() + " materials successfully loaded from the file.");

        } catch (IOException e) {
            System.err.println("Error while reading the file: " + e.getMessage());
        }
    }
}