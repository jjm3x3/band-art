import processing.core.PApplet;
import processing.core.PVector;


public class MousyProtoCircleScene {

    PApplet parent;

    MousyProtoCircleScene(PApplet parent) {
        this.parent = parent;
    }
    void draw() {
        int n = 6;
        double theta = 2*Math.PI/n;
        double radialOffset = Math.max(parent.pmouseX,0);
        double angularOffset = parent.pmouseY/2;

        for(int i = 0; i < n; ++i) {
            double x = parent.width/2 + radialOffset * Math.sin(i * theta+angularOffset);
            double y = parent.height/2 + radialOffset* Math.cos(i*theta+angularOffset);
            PVector pos = new PVector((float)x,(float)y);
            Circle c = new Circle(parent, pos,(float)50);
            c.display();
        }
    }
}
