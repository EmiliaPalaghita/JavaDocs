package exercise3;

/**
 * Created by Emilia.Palaghita on 07-Jul-17.
 */
public class Student {

    private String firstName;
    private String lastName;

    public Student(String last, String first) {
        firstName = first;
        lastName = last;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "First Name: " + firstName + ", Last Name: " + lastName;
    }
}
