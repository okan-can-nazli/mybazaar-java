public class DVD extends  Material {

    //special DVD attributes
    private String director;
    private int duration;

    public DVD(String id, String title, String director, int duration, int publicationYear) {
        super(id, title, publicationYear);
        this.director = director;
        this.duration = duration;
    }

    //getters
    public String getDirector() {
        return director;
    }

    public int getDuration() {
        return duration;
    }

    //exclusive method of DVD's
    public void watch() { //no specification in pdf for "watch"
        System.out.println("Now watching: " + getTitle() + " directed by " + director);
        System.out.println("Duration: " + duration + " minutes");
    }

    // ------MATERIAL ABSTRACT METHODS-----

    @Override
    public String getDetails() {
        return String.format("ID: %s | Title: %s | Director: %s | Duration: %d min | Year: %d",
                getId(), getTitle(), director, duration, getPublicationYear());
    }

    @Override
    public String toFileFormat() {
        return "DVD," + getId() + "," + getTitle() + "," + director + "," +
                duration + "," + getPublicationYear();
    }

    @Override
    public String getMaterialType() {
        return "DVD";
    }

}
