// English version:For class named "Book" go to "Material" abstract class and get new method names from there after that declare these methods bodies as that defined in "interfaces"
//subclass (the child),every material is not a book however  every book is a material.
public class Book extends Material implements Borrowable, Reservable {

    //other vars declared in Material class as "non-book specific"
    private String author;
    private boolean isBorrowed;
    private int borrowDays;


    // Reservation variables (for Reservable interface)
    private boolean reserved;
    private String reservationHolder;



    //store book info and set it to NOT borrowed
    //Constructor
    public Book(String isbn, String title, String author, int publicationYear) {

        super(isbn, title, publicationYear);  // call constructor's from superclass (imagine the child is crying,what a imagination ha?)

        this.author = author;
        this.isBorrowed = false;
        this.borrowDays = 14;  // no specification in pdf
        this.reserved = false;
        this.reservationHolder = null;
    }

// ONLY book-specified getter
    public String getAuthor() {
        return author;
    }




    //--------INTERFACE METHODS-------

    //BORROWABLE INTERFACE METHODS


    //@Override provides to declare new bodies to the methods
    @Override
    public void borrow(){
        if (!isBorrowed){
            isBorrowed = true;
            System.out.println("Book borrowed successfully!");
        }
        else{
            System.out.println("Sorry,The Book already borrowed :(");
        }
    }


    @Override
    public void returnItem() {
        if (isBorrowed) {
            isBorrowed = false;
            borrowDays = 14;  // Reset days
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Book is already available.");
        }
    }

    @Override
    public boolean isAvailableToBorrow() {
        return !isBorrowed;
    }

    @Override
    public int getRemainingDays() {
        return isBorrowed ? borrowDays : 0;
    }



    // RESERVABLE INTERFACE METHODS

    @Override
    public void reserve(String userName) {
        if (!reserved) {
            reserved = true;
            reservationHolder = userName;
            System.out.println("Book reserved successfully for " + userName);
        } else {
            System.out.println("Book is already reserved by " + reservationHolder);
        }
    }

    @Override
    public void cancelReservation() {
        if (reserved) {
            reserved = false;
            reservationHolder = null;
            System.out.println("Reservation cancelled successfully!");
        } else {
            System.out.println("No reservation to cancel.");
        }
    }

    @Override
    public boolean isReserved() {
        return reserved;
    }

    @Override
    public String getReservationHolder() {
        return reservationHolder;
    }



    // ------MATERIAL ABSTRACT METHODS-----

    @Override
    public String getDetails() {
        String status = isBorrowed ? "Unavailable" : "Available";
        String reservationInfo = reserved ? " | Reserved by: " + reservationHolder : "";

        return String.format("ISBN: %s | Title: %s | Author: %s | Year: %d | Status: %s%s",
                getId(), getTitle(), author, getPublicationYear(), status, reservationInfo);
    }

    @Override
    public String toFileFormat() {
        return "Book," + getId() + "," + getTitle() + "," + author + "," +
                getPublicationYear() + "," + isBorrowed + "," + reserved + "," +
                (reservationHolder != null ? reservationHolder : "null");
    }

    @Override
    public String getMaterialType() {
        return "Book";
    }

    // Helper methods for loading from file
    public void setIsBorrowed(boolean borrowed) {
        this.isBorrowed = borrowed;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public void setReservationHolder(String holder) {
        this.reservationHolder = holder;
    }
}
