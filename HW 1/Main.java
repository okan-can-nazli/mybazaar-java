import java.util.Scanner;
import java.util.InputMismatchException;
import java.time.Year;

public class Main{
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        //Menu
        while (true){

            //display options
            System.out.println(
                    "---MAIN MENU---\n\n" +
                    "1. Add Book\n" +
                    "2. List All Books\n" +
                    "3. List Available Books\n" +
                    "4. Borrow Book\n" +
                    "5. Return Book\n" +
                    "0. Exit\n"
            );
            System.out.print("Select an option (0-5): ");


            try {
                int optionInput = scanner.nextInt();
                scanner.nextLine(); // stands for "pressing enter"

                switch (optionInput) {
                    case 1:

                        System.out.println("\n--- Add New Book ---");

                        System.out.print("Enter ISBN: ");
                        String isbn = scanner.nextLine();

                        System.out.print("Enter Title: ");
                        String title = scanner.nextLine();

                        System.out.print("Enter Author: ");
                        String author = scanner.nextLine();




                        // I just wanted to try an additional condition for user inputs to improve learning process

                        int year = 0;
                        boolean isValidYear = false;
                        int currentYear = java.time.Year.now().getValue(); // Get the current year dynamically

                        while (!isValidYear) {
                            System.out.print("Enter Publication Year: ");

                            if (scanner.hasNextInt()) { // necessary to pass Strings to outer else condition
                                year = scanner.nextInt();
                                scanner.nextLine();

                                if (year <= 0) { // left bracket
                                    System.out.println("Error: Publication Year must be a positive number.");

                                } else if (year > currentYear) { // right bracket
                                    System.out.println("Error: Publication Year cannot be after the current year");

                                } else {
                                    isValidYear = true;
                                }
                            } else {
                                // Handle non-integer input
                                System.out.println("Error: Invalid input. Publication Year must be an integer.");
                                scanner.nextLine();
                            }
                        }
                        // Create Book object
                        Book newBook = new Book(isbn, title, author, year);

                        // Add to library
                        library.addBook(newBook);

                        System.out.println("\n\n\n"); // There is no "\n" * 3 in java

                        continue;

                    case 2:

                        System.out.println("---List of All Books---\n");

                        library.listAllBooks();

                        System.out.println("\n");


                        System.out.println("------------------------\n"); // There is no "\n" * 3 in java

                        continue;

                    case 3:

                        System.out.println("---List of AVAILABLE Books---\n");

                        library.listAvailableBooks();

                        System.out.println("\n------------------------------\n");

                        continue;

                    case 4:

                        System.out.println("Enter your ISBN of your book to borrow it: ");

                        String borrowIsbn = scanner.nextLine();

                        library.borrowBook(borrowIsbn);

                        System.out.println("\n");


                        continue;

                    case 5:

                        System.out.println("Enter your ISBN of your book to return it: ");

                        String returnIsbn = scanner.nextLine();

                        library.returnBook(returnIsbn);

                        System.out.println("\n");


                        continue;

                    case 0:

                        System.out.println("\nExiting from the Library Management System");

                        scanner.close(); //Very efficient way to use

                        return; //return provides to exist instantly

                    default:
                        System.out.println("Please Enter A Valid Integer Input,(0-5)");
                }
            }catch (InputMismatchException exception) {
                System.out.println("Invalid input! Please enter a integer.");

            }


        }

    }

}
