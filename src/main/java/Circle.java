import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;
import processing.core.PVector;

import java.util.function.Consumer;

public class Circle {

    PApplet parent;
    PVector center;
    PShape shape;

    public Circle(PApplet parent, PVector point, float radius) {
        this.parent = parent;
        this.center = point;

        this.shape = parent.createShape(PConstants.GROUP);

        PShape kidA = parent.createShape(PConstants.ELLIPSE, (float) center.x, (float) center.y, radius, radius);
        kidA.setFill(false);
        kidA.setStroke(parent.color(255,0,0));

        shape.addChild(kidA);
        //this.shape.setFill(parent.color(125,0,0));


//        this.shape.endShape();
    }

    void move(PVector p) {
        PVector thiscenter = center;
        center = p;
        float movex = (float) (p.x - thiscenter.x);
        float movey = (float) (p.y - thiscenter.y);
        shape.translate(movex, movey);
    }

    void display() {
        parent.shape(shape,0,0);
    }

    @Override
    public String toString() {
        return String.format("Circle(%s,%s)",this.center.x,this.center.y);
    }

    public void apply(Consumer<PShape> f) {
        f.accept(shape);
    }
}
