package ro.teamnet.zerotohero.oop.graphicshape;

import com.sun.org.apache.bcel.internal.generic.NEW;
import ro.teamnet.zerotohero.NewException;

import java.lang.Math;

/**
 * Created by Emilia.Palaghita on 7/4/2017.
 */
public class Circle extends Shape {
    private int xPos;
    private int yPos;
    private int radius;

    public Circle() {
        xPos = 1;
        yPos = 1;
        radius = 1;
    }

    @Override
    public String toString() {
        return "center = (" + xPos + ", " +  yPos + ") and radius = " + radius;
    }

    public Circle(int x) {
        xPos = x;
        yPos = 1;
        radius = 1;
    }

    public Circle(int x, int y) {
        xPos = x;
        yPos = y;
        radius = 1;
    }

    public Circle(int x, int y, int r) {
        xPos = x;
        yPos = y;
        try {
            if(r <= 0) {
                throw new NewException("wrong parameter");
            }
            radius = r;
        } catch (NewException e) {
            System.out.println(e.getMessage());
        }

    }

    public void fillColor() {
        System.out.println(super.color);
    }

    public void fillColor(int c) {
        color = c;
        System.out.println("The circle color is now " + c);
    }

    public void fillColor(float s) {
        saturation = s;
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }

}
