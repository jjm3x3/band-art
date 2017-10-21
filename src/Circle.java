import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;

public class Circle {

    PApplet parent;
    Point center;
    PShape shape;
    public Circle(PApplet parent, Point point) {
        this.parent = parent;
        this.center = point;
        this.shape = parent.createShape(PConstants.ELLIPSE, (float) center.x, (float) center.y, 50, 50);


        this.shape.setFill(parent.color(132,234,10));
        //this.shape.setFill(false);
        //this.shape.endShape();
    }

    void display() {
        parent.shape(shape,0,0);
    }
}
