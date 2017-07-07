package exercise3;

/**
 * Created by Emilia.Palaghita on 07-Jul-17.
 */
public class ClasaB extends Student{
    public ClasaB(String last, String first) {
        super(last, first);
    }

    @Override
    public int hashCode() {
        return this.getFirstName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null || obj.getClass()!= this.getClass()) {
            return false;
        }
        ClasaB c = (ClasaB) obj;
        return c.getFirstName().equals(this.getFirstName()) && c.getLastName().equals(this.getLastName());
    }
}
