package ro.teamnet.zerotohero.oop.graphicshape.runapp;

import ro.teamnet.zerotohero.oop.graphicshape.*;
//import ro.teamnet.zerotohero.Canvas;

/**
 * Created by Emilia.Palaghita on 7/4/2017.
 */
public class RunApp {
    public static void main(String[] args) {
        Circles circles = new Circles();
        System.out.println(circles.getAreaPub());
        circles.getAreaDef();
        //zerotohero.Canvas canvas = new zerotohero.Canvas();
//        canvas.paint();
        Shape shape = new Circle(10);
        System.out.println(shape.toString());
        ShapeBehaviour beh = new Square(10);
        System.out.println(beh.toString());

        Object p1 = new Point(10, 20);
        Object p2 = new Point(50, 100);
        Object p3 = new Point(10, 20);
        System.out.println("p1 equals p2 is " + p1.equals(p2));
        System.out.println("p1 equals p3 is " + p1.equals(p3));

        Circle c = new Circle(1,2,0);
    }
}
