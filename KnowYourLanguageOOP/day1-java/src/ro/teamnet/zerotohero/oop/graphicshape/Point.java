package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by Emilia.Palaghita on 7/4/2017.
 */
public class Point {
    int xPos;
    int yPos;
    public Point(int x, int y) {
        xPos = x;
        yPos = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (xPos != point.xPos) return false;
        return yPos == point.yPos;
    }

}
