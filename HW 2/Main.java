import java.util.Scanner;
import java.util.InputMismatchException;
import java.time.Year;



public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(
                    "--- LIBRARY MANAGEMENT SYSTEM ---\n\n" +
                            "1. Add Book\n" +
                            "2. Add Magazine\n" +
                            "3. Add DVD\n" +
                            "4. List All Materials\n" +
                            "5. List by Type\n" +
                            "6. Borrow Material\n" +
                            "7. Return Material\n" +
                            "8. Reserve Material\n" +
                            "0. Exit\n"
            );
            System.out.print("Select an option (0-8): ");

            try {
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1: // Add Book
                        System.out.println("\n--- Add New Book ---");

                        System.out.print("Enter ISBN: ");
                        String isbn = scanner.nextLine();

                        System.out.print("Enter Title: ");
                        String bookTitle = scanner.nextLine();

                        System.out.print("Enter Author: ");
                        String author = scanner.nextLine();

                        int bookYear = getValidYear(scanner);

                        Book newBook = new Book(isbn, bookTitle, author, bookYear);
                        library.addMaterial(newBook);

                        System.out.println("\n");
                        break;

                    case 2: // Add Magazine
                        System.out.println("\n--- Add New Magazine ---");

                        System.out.print("Enter Magazine ID: ");
                        String magId = scanner.nextLine();

                        System.out.print("Enter Title: ");
                        String magTitle = scanner.nextLine();

                        System.out.print("Enter Issue Number: ");
                        int issueNum = scanner.nextInt();
                        scanner.nextLine();

                        int magYear = getValidYear(scanner);

                        Magazine newMagazine = new Magazine(magId, magTitle, issueNum, magYear);
                        library.addMaterial(newMagazine);

                        System.out.println("\n");
                        break;

                    case 3: // Add DVD
                        System.out.println("\n--- Add New DVD ---");

                        System.out.print("Enter DVD ID: ");
                        String dvdId = scanner.nextLine();

                        System.out.print("Enter Title: ");
                        String dvdTitle = scanner.nextLine();

                        System.out.print("Enter Director: ");
                        String director = scanner.nextLine();

                        System.out.print("Enter Duration (minutes): ");
                        int duration = scanner.nextInt();
                        scanner.nextLine();

                        int dvdYear = getValidYear(scanner);

                        DVD newDVD = new DVD(dvdId, dvdTitle, director, duration, dvdYear);
                        library.addMaterial(newDVD);

                        System.out.println("\n");
                        break;

                    case 4: // List All Materials
                        System.out.println("\n--- List of All Materials ---\n");
                        library.listAllMaterials();
                        System.out.println("\n");
                        break;

                    case 5: // List by Type
                        System.out.println("\n--- List Materials by Type ---");
                        System.out.println("1. Books");
                        System.out.println("2. Magazines");
                        System.out.println("3. DVDs");
                        System.out.print("Select type: ");

                        int typeChoice = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println();
                        switch (typeChoice) {
                            case 1:
                                library.listByType("Book");
                                break;
                            case 2:
                                library.listByType("Magazine");
                                break;
                            case 3:
                                library.listByType("DVD");
                                break;
                            default:
                                System.out.println("Invalid type selection.");
                        }
                        System.out.println("\n");
                        break;

                    case 6: // Borrow Material
                        System.out.print("\nEnter the ID of the material to borrow: ");
                        String borrowId = scanner.nextLine();
                        library.borrowMaterial(borrowId);
                        System.out.println("\n");
                        break;

                    case 7: // Return Material
                        System.out.print("\nEnter the ID of the material to return: ");
                        String returnId = scanner.nextLine();
                        library.returnMaterial(returnId);
                        System.out.println("\n");
                        break;

                    case 8: // Reserve Material
                        System.out.print("\nEnter the ID of the book to reserve: ");
                        String reserveId = scanner.nextLine();

                        System.out.print("Enter your name: ");
                        String userName = scanner.nextLine();

                        library.reserveMaterial(reserveId, userName);
                        System.out.println("\n");
                        break;

                    case 0: // Exit
                        System.out.println("\nExiting Library Management System. Goodbye!");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid option! Please enter a number between 0-8.\n");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid integer.\n");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    // Helper method to get valid year input (improved version on HW 1)
    private static int getValidYear(Scanner scanner) {
        int year = 0;
        boolean isValid = false;
        int currentYear = java.time.Year.now().getValue();

        while (!isValid) {
            System.out.print("Enter Publication Year: ");

            if (scanner.hasNextInt()) {
                year = scanner.nextInt();
                scanner.nextLine();

                if (year <= 0) {
                    System.out.println("Error: Publication Year must be a positive number.");
                } else if (year > currentYear) {
                    System.out.println("Error: Publication Year cannot be after the current year.");
                } else {
                    isValid = true;
                }
            } else {
                System.out.println("Error: Invalid input. Publication Year must be an integer.");
                scanner.nextLine();
            }
        }

        return year;
    }
}