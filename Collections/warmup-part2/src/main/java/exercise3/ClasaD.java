package exercise3;

/**
 * Created by Emilia.Palaghita on 07-Jul-17.
 */
public class ClasaD extends Student {
    public ClasaD(String last, String first) {
        super(last, first);
    }

    @Override
    public int hashCode() {
        int result = getFirstName().hashCode();
        result = 17 * result + getLastName().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        ClasaD c = (ClasaD) obj;
        return c.getFirstName().equals(this.getFirstName()) &&
                c.getLastName().equals(this.getLastName());
    }
}
