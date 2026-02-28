abstract class OfficeSupplies extends Item {
    protected int releaseDate;
    protected String coverTitle;

    public OfficeSupplies(double price, int releaseDate, String coverTitle) {
        super(price);
        this.releaseDate = releaseDate;
        this.coverTitle = coverTitle;
    }

    protected String getBaseInfo() {
        String info = "Item ID: " + itemID + "\n";
        info += "Price: " + price + " $\n";
        info += "Release Date: " + releaseDate + "\n";
        info += "Title: " + coverTitle + "\n";
        return info;
    }
}

class Book extends OfficeSupplies {
    private String publisher;
    private String authors;
    private int pages;

    public Book(double price, int releaseDate, String coverTitle,
                String publisher, String authors, int pages) {
        super(price, releaseDate, coverTitle);
        this.publisher = publisher;
        this.authors = authors;
        this.pages = pages;
    }

    @Override
    public String displayInfo() {
        String info = "Type: Book\n";
        info += getBaseInfo();
        info += "Publisher: " + publisher + "\n";
        info += "Author: " + authors + "\n";
        info += "Page: " + pages + " pages";
        return info;
    }
}

class CDDVD extends OfficeSupplies {
    private String composer;
    private String songs;

    public CDDVD(double price, int releaseDate, String coverTitle,
                 String composer, String songs) {
        super(price, releaseDate, coverTitle);
        this.composer = composer;
        this.songs = songs;
    }

    @Override
    public String displayInfo() {
        String info = "Type: CDDVD\n";
        info += getBaseInfo();
        info += "Composer: " + composer + "\n";
        info += "Songs: " + songs;
        return info;
    }
}