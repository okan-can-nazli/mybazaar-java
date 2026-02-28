
// magazines also a material like books
public class Magazine extends Material implements Borrowable{ //ONLY BORROWABLE

    private int issueNumber;  // instead of author,this selection is open to criticize
    private boolean isBorrowed;
    private int borrowDays;

    public Magazine(String id, String title, int issueNumber, int publicationYear){

        super(id, title, publicationYear); //super ALWAYS MUST BE ON TOP of the constructor

        this.issueNumber = issueNumber;
        this.borrowDays = 7; //no specification on pdf
        isBorrowed = false;
    }

    //getter
    public int getIssueNumber(){return issueNumber;}




    //--------INTERFACE METHODS-------

    //BORROWABLE INTERFACE METHODS


    //@Override provides to declare new bodies to the methods
    @Override
    public void borrow(){
        if (!isBorrowed){
            isBorrowed = true;
            System.out.println("Magazine borrowed successfully!");
        }
        else{
            System.out.println("Sorry,The Magazine already borrowed :(");
        }
    }


    @Override
    public void returnItem() {
        if (isBorrowed) {
            isBorrowed = false;
            borrowDays = 7;  // Reset days
            System.out.println("Magazine returned successfully!");
        } else {
            System.out.println("Magazine is already available.");
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

    // ------MATERIAL ABSTRACT METHODS-----

    @Override
    public String getDetails() {
        String status = isBorrowed ? "Unavailable" : "Available";

        return String.format("ID: %s | Title: %s | Issue #%d | Year: %d | Status: %s",
                getId(), getTitle(), issueNumber, getPublicationYear(), status);
    }

    @Override
    public String toFileFormat() {
        return "Magazine," + getId() + "," + getTitle() + "," + issueNumber + "," +
                getPublicationYear() + "," + isBorrowed;
    }

    @Override
    public String getMaterialType() {
        return "Magazine";
    }

    // Helper methods for loading from file
    public void setIsBorrowed(boolean borrowed) {
        this.isBorrowed = borrowed;

    }

}

