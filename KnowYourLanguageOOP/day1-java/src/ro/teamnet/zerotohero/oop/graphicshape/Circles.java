package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by Emilia.Palaghita on 7/4/2017.
 */
public class Circles {
    public double getAreaPub() {
        Circle c = new Circle();
        return c.area();
    }

    public void getAreaDef() {
        Circle c = new Circle();
        c.fillColor();
        c.fillColor(3);
        c.fillColor((float)0.2);
    }

}
