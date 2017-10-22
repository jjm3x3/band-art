import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;
public class CircleScene {

    PApplet parent;
    List<Circle> shapes;

    CircleScene(PApplet parent) {
        this.parent = parent;
        setup();
    }

    void setup() {
        int n = 6;
        double theta = 2*Math.PI/n;
        double radialOffset = Math.max(parent.pmouseX,0);
        double angularOffset = parent.pmouseY/2;
        shapes = new ArrayList<>();
        for(int i = 0; i < n; ++i) {
            double x = parent.width/2 + radialOffset * Math.sin(i * theta+angularOffset);
            double y = parent.height/2 + radialOffset* Math.cos(i*theta+angularOffset);
            Point pos = new Point(x,y);
            Circle c = new Circle(parent, pos);
            shapes.add(c);
        }
    }

    void update() {
        int n = 6;
        double theta = 2*Math.PI/n;
        double radialOffset = Math.max(parent.pmouseX,0);
        double angularOffset = parent.pmouseY/2;
        int i = 0;
        for(Circle c: shapes) {

            //String str = String.format("%d, %s",i,c.toString());
            //System.out.println(str);

            double x = parent.width/2 + radialOffset * Math.sin(i * theta+angularOffset);
            double y = parent.height/2 + radialOffset* Math.cos(i*theta+angularOffset);
            i++;
            //Point pos = new Point(x,y);
            c.move(new Point(x,y));
        }
    }

    void draw() {
        parent.background(0);
        update();
        for(Circle c: shapes) {
            c.display();
        }
    }
}
