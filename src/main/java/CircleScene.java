import processing.core.PApplet;
import oscP5.*;
import java.util.ArrayList;
import java.util.List;
public class CircleScene  implements oscP5.OscEventListener {

    PApplet parent;
    List<Circle> shapes;
    Float radialOffset;
    Float angularOffset;

    Oscilator radialOscilator;
    Oscilator angularOscilator;

    int numCircles = 6;



    CircleScene(PApplet parent) {
        this.parent = parent;
        setup();
    }

    void setup() {
        parent.background(0);
        double theta = 2*Math.PI/numCircles;
        radialOffset = (float) 0;
        angularOffset = (float) 0;
        radialOscilator = new Oscilator(parent);
        angularOscilator = new Oscilator(parent);
        shapes = new ArrayList<>();
        for(int i = 0; i < numCircles; ++i) {
            double x = parent.width/2 + (radialOffset) * Math.sin(i * theta + angularOffset);
            double y = parent.height/2 + (radialOffset) * Math.cos(i * theta + angularOffset);
            Point pos = new Point(x, y);
            Circle c = new Circle(parent, pos);
            shapes.add(c);
        }
    }

    void update() {
        double theta = 2*Math.PI/numCircles;
        int i = 0;
//        oscP5.OscEventListener listener;
//        controler.addListener(listener);
        //System.out.print("radialOffset:"+this.radialOffset+" - ");
        //System.out.println("angularOffset:"+this.angularOffset);
        for(Circle c: shapes) {
            double x = parent.width/2 + (radialOffset+100*radialOscilator.value()) * Math.sin(i * theta  + angularOffset + angularOscilator.value());
            double y = parent.height/2 + (radialOffset+100*radialOscilator.value())* Math.cos(i * theta  + angularOffset + angularOscilator.value());
//            System.out.println("What are our x: " + x + ", y: " + y);
//            double x = parent.width/2 + (radialOffset * radialOscilator.value()) * Math.sin(i * theta  + angularOffset * angularOscilator.value());
//            double y = parent.height/2 + (radialOffset * radialOscilator.value())* Math.cos(i * theta  + angularOffset * angularOscilator.value());
            i++;
            c.move(new Point(x,y));
        }
    }

    void draw() {
        update();
        for(Circle c: shapes) {
            c.display();
        }
    }

    /* incoming osc message are forwarded to the oscEvent method. */
    // NB: the switching below appears to require if-thens.
    public void oscEvent(OscMessage oscmsg) {
        String addr = oscmsg.addrPattern();
        //        System.out.println(addr);
        // Uses the Automat5 controller of touchOsc
        // This is the left hand xy slider
        if(addr.equals("/3/xyM_l")) {
              float  lxval  = parent.width * oscmsg.get(1).floatValue();
              float  lyval  = 25 * oscmsg.get(0).floatValue();
              this.radialOffset = Math.max(lxval,0);
              this.angularOffset = lyval/2;
              // This is the right hand xy slider
          } else if(addr.equals("/3/xyM_r")) {
              float  rxval  = 1 * oscmsg.get(1).floatValue();
              float  ryval  = 1 * oscmsg.get(0).floatValue();
              radialOscilator.frequency = (double)rxval;
              angularOscilator.frequency = (double)ryval;
          } else {}
    }

    @Override
    public void oscStatus(OscStatus oscStatus) {

    }
}
