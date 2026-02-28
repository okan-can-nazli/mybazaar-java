//SuperClass (the mother)
public abstract class Material {

    //intersection of all materials attributes
    private String id;
    private String title;
    private int publicationYear;
    //if there is no more features on the project we can also add "private String creator"

    public Material(String id,String title, int publicationYear){
        this.id = id;
        this.title = title;
        this.publicationYear = publicationYear;
    }

    public String getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }


    // we will implement and use these methods in book class,currently they CAN NOT perform any action just about existence

    public abstract String getDetails();

    public abstract String toFileFormat();

    public abstract String getMaterialType();





}
