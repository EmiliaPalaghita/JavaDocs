package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by Emilia.Palaghita on 7/4/2017.
 */
public class Shape extends AbstractShape implements ShapeBehaviour{

    protected int color;
    protected float saturation;

    public double area() {
        return 0;
    }
    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getSaturation() {
        return saturation;
    }

    public void setSaturation(float saturation) {
        this.saturation = saturation;
    }

}
