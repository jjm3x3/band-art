import processing.core.PApplet;
import oscP5.*;
import java.util.ArrayList;
import java.util.List;
public class CircleScene {

    PApplet parent;
    List<Circle> shapes;
    oscP5.OscP5 controler;
    Float radialOffset;
    Float angularOffset;

    CircleScene(PApplet parent) {
        this.parent = parent;
        setup();
    }

    void setup() {
        int n = 6;
        double theta = 2*Math.PI/n;
        this.radialOffset = (float)0;//Math.max(parent.pmouseX,0);
        this.angularOffset = (float)0;//parent.pmouseY/2;
        oscP5.OscP5 controler = new OscP5(this,12348);
        controler.status(0);
        System.out.println(controler.properties());
        shapes = new ArrayList<>();
        for(int i = 0; i < n; ++i) {
            double x = parent.width/2 + radialOffset * Math.sin(i * theta + angularOffset);
            double y = parent.height/2 + radialOffset * Math.cos(i * theta + angularOffset);
            Point pos = new Point(x,y);
            Circle c = new Circle(parent, pos);
            shapes.add(c);
        }
    }

    void update() {
        int n = 6;
        double theta = 2*Math.PI/n;
//        double radialOffset = Math.max(parent.pmouseX,0);
//        double angularOffset = parent.pmouseY/2;
        int i = 0;
//        oscP5.OscEventListener listener;
//        controler.addListener(listener);
        //System.out.print("radialOffset:"+this.radialOffset+" - ");
        //System.out.println("angularOffset:"+this.angularOffset);
        for(Circle c: shapes) {

            //String str = String.format("%d, %s",i,c.toString());
            //System.out.println(str);

            double x = parent.width/2 + radialOffset * Math.sin(i * theta + angularOffset);
            double y = parent.height/2 + radialOffset* Math.cos(i*theta + angularOffset);
            i++;

            c.move(new Point(x,y));
        }
    }

    void draw() {
        //parent.background(0);
        update();

        for(Circle c: shapes) {
            c.display();
        }
    }

    /* incoming osc message are forwarded to the oscEvent method. */
    void oscEvent(OscMessage oscmsg) {
        String addr = oscmsg.addrPattern();
//        System.out.println(addr);
        // Uses the Automat5 controller of touchOsc
        switch (addr) {
            // This is the left hand xy slider
            case "/3/xyM_l": {
                float  xval  = 300 * oscmsg.get(0).floatValue();
                float  yval  = 25 * oscmsg.get(1).floatValue();
                this.radialOffset = Math.max(xval,0);
                this.angularOffset = yval/2;
            }
        }

    }
}
