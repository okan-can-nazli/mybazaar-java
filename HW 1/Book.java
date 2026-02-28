public class Book {

    private String isbn, title, author;
    private int publicationYear;
    private boolean isBorrowed;
    //"private" keeps attributes (class obj properties) in class


    //store book info and set it to NOT borrowed
    //Constructor
    public Book(String isbn, String title, String author, int publicationYear) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;

        //no need to use this. cause it a boolean
        isBorrowed = false; //this "attribute" is not a attribute technically,I guess... EDİT:ı was wrong ıt is a pure attribute
    }

    //getters
    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public boolean getIsBorrowed(){
        return isBorrowed;
    }


    //setter
    public void setIsBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    //Checkers
    //book info
    public String toString() {

        String status = isBorrowed ? "Unavailable" : "Available"; //check book in the lib or not

        return String.format("ISBN: %s | Title: %s | Author: %s |Year: %d | Status: %s", getIsbn(), getTitle(), getAuthor(), getPublicationYear(), status); // (f-method) in Python is (String."F"ormat) in Java, now it make sense
    }

    public String toFileFormat() { //note for myself:This method is also creating file
        return getIsbn() + "," + getTitle() + "," + getAuthor() + "," + getPublicationYear() + "," + isBorrowed; //personally ı like to use String.format but I guess this is the java standart
    }

}