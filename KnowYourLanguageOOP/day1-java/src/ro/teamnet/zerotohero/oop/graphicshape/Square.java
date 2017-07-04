package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by Emilia.Palaghita on 7/4/2017.
 */
public class Square extends Shape {
    private int side;
    public Square(int s) {
        this.side = s;
    }

    @Override
    public double area() {
        return (double) side * side;
    }
}
