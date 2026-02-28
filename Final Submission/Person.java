import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Person {
    protected String name;
    protected String email;
    protected Date dateOfBirth;

    public Person(String name, String email, Date dateOfBirth) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public void displayPersonalInfo() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        System.out.println("Name: " + name);
        System.out.println("E-mail: " + email);
        System.out.println("Date of Birth: " + sdf.format(dateOfBirth));
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }
}