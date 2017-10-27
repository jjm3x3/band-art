import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;
import processing.core.PVector;

public class Circle {

    PApplet parent;
    PVector center;
    PShape shape;
    float scale;
    public Circle(PApplet parent, PVector point, float scale) {
        this.parent = parent;
        this.center = point;
        this.scale = scale;
        this.shape = parent.createShape(PConstants.ELLIPSE, (float) center.x, (float) center.y, scale, scale);
        this.shape.setFill(false);
        this.shape.setStroke(parent.color(255,0,0));
        this.shape.strokeWeight(10);

        //this.shape.setFill(parent.color(125,0,0));


        this.shape.endShape();
    }

    void move(PVector p) {
        PVector thiscenter = center;
        center = p;
        float movex = (float) (p.x - thiscenter.x);
        float movey = (float) (p.y - thiscenter.y);
        this.shape.translate(movex, movey);
    }

    void display() {
        parent.shape(shape,0,0);
    }

    @Override
    public String toString() {
        return String.format("Circle(%s,%s)",this.center.x,this.center.y);
    }
}
