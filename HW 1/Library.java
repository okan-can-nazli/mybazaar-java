import java.util.ArrayList ; //arrays
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException; //err handling
import java.io.File; //checks file existence

public class Library {

    private ArrayList<Book> books;

    public static final String FILE_NAME = "books.txt";

    public Library() {

        this.books = new ArrayList<>();

        loadBooksFromFile();
    }

    public void addBook(Book newBook) { //Book means: input must be a "Book" obj

        if (findBook(newBook.getIsbn()) != null) { // User CAN NOT use same ISBN  for 2 diffrent books
            System.out.println("Error: A book with ISBN " + newBook.getIsbn() + " already exists in the library!");
            return;
        }


        books.add(newBook);
        System.out.println("New book successfully added to library!");

        saveToFile();
    }

    public void listAllBooks() {
        if (books.isEmpty()) {
            System.out.println("There is no book in the library :(");
        } else {
            for (int i = 0; i < books.size(); i++) {
                System.out.println(i + 1 + ". " + books.get(i));
            }

        }
    }

    public void listAvailableBooks() {

        if (books.isEmpty()) {
            System.out.println("There is no book in the library :(");
        } else {
            int counter = 0;

            for (Book book : books) { // "Book" stands for a NEW data type that is the class we created
                if (!book.getIsBorrowed()) {
                    counter++;
                    System.out.println(counter + ". " + book);
                }
            }

            if (counter == 0) {
                System.out.println("There is no AVAILABLE book in the library :(");
            }
        }

    }

    public Book findBook(String isbn) {
        for (Book book : books) {
            if (isbn.equals(book.getIsbn())) {
                return book;
            }
        }
        return null;
    }

    public void borrowBook(String isbn) {
        Book foundBook = findBook(isbn); // to make code more efficient

        if (foundBook == null) {
            System.out.println("Book with ISBN " + isbn + " not found.");
        } else if (foundBook.getIsBorrowed()) {
            System.out.println("Warning: Book " + foundBook.getTitle() + " is already borrowed.");

        } else {
            foundBook.setIsBorrowed(true);
            saveToFile();
            System.out.println("Successfully borrowed the book ISBN: " + foundBook.getIsbn() + " , " + "Title:" + foundBook.getTitle());
        }
    }

    public void returnBook(String isbn) {
        Book foundBook = findBook(isbn);

        if (foundBook == null) {
            System.out.println("Book with ISBN " + isbn + " not found.");
        } else if (!foundBook.getIsBorrowed()) {
            System.out.println("Warning: Book '" + foundBook.getTitle() + "' is already available.");

        } else {
            foundBook.setIsBorrowed(false);
            saveToFile();
            System.out.println("Successfully returned the book ISBN: " + foundBook.getIsbn() + " , " + "Title:" + foundBook.getTitle());

        }

    }


    // I/O methods *** The most challenged part for me

    private void saveToFile() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) { //checks file existence , DO NOT USE "books.txt" instead of FILE_NAME
            for (Book book : books) {
                String line = book.toFileFormat();

                writer.write(line);
                writer.write("\n");
            }
        } catch (IOException exception) {
            System.err.println("Error while writing the file: " + exception.getMessage()); // .getMessage displays error reason
        }
    }

    private void loadBooksFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("File not found :( ");
            return;
        }

        try (FileReader reader = new FileReader(FILE_NAME)) {

            // Read entire file
            StringBuilder fileContent = new StringBuilder(); // It can be build with arrays too but arrays do not contain .append method,we just make our way easier
            int charCode;

            /*
            **What it does:**
            - Creates a variable to store each character we read
            - Named `charCode` because it stores the numeric code of a character

            **Why `int` not `char`?**
            - `reader.read()` returns an `int`, not a `char`
            - Returns a number that represents a character
            - Returns `-1` when file ends (and -1 can't be a char!)

            **Example character codes:**
            - 'A' = 65
            - 'a' = 97
            - '0' = 48
            - '\n' = 10
            - End of file = -1

            **Current state:**
             */


            while ((charCode = reader.read()) != -1) {
                fileContent.append((char) charCode);
            }

            // Convert to string and split into lines
            String allText = fileContent.toString();
            String[] lines = allText.split("\n");

            // Process each line
            for (String line : lines) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");

                if (parts.length != 5) {
                    System.err.println("Skipping invalid line (wrong field count): " + line);
                    continue;
                }

                try {
                    String isbn = parts[0].trim();
                    String title = parts[1].trim();
                    String author = parts[2].trim();

                    //convert string elements they own data type
                    int year = Integer.parseInt(parts[3].trim());
                    boolean borrowed = Boolean.parseBoolean(parts[4].trim());

                    Book book = new Book(isbn, title, author, year);
                    book.setIsBorrowed(borrowed);
                    books.add(book);

                } catch (NumberFormatException exception) {
                    System.err.println("Skipping invalid line (bad year):  "  + exception.getMessage() + "  " + line);
                }
            }

            System.out.println(books.size() + " Books successfully loaded from the file.");

        } catch (IOException exception) {
            System.err.println("Error while reading the file: " + exception.getMessage());
        }
    }
}

