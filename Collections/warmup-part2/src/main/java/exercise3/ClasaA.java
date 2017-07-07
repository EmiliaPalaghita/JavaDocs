package exercise3;

/**
 * Created by Emilia.Palaghita on 07-Jul-17.
 */
public class ClasaA extends Student {
    public ClasaA(String last, String first) {
        super(last, first);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null || obj.getClass()!= this.getClass()) {
            return false;
        }
        ClasaA c = (ClasaA) obj;
        return c.getFirstName().equals(this.getFirstName());
    }

    @Override
    public int hashCode() {
        return this.getFirstName().hashCode();
    }
}
