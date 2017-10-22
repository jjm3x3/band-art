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

    Oscilator radialOscilator;
    Oscilator angularOscilator;

    CircleScene(PApplet parent) {
        this.parent = parent;
        setup();
    }

    void setup() {
        parent.background(0);
        int n = 6;
        double theta = 2*Math.PI/n;
        this.radialOffset = (float)0;//Math.max(parent.pmouseX,0);
        this.angularOffset = (float)0;//parent.pmouseY/2;
        oscP5.OscP5 controler = new OscP5(this,12348);
        this.radialOscilator = new Oscilator(parent);//, controler);
        this.angularOscilator = new Oscilator(parent);//, controler);
        controler.status(0);
        System.out.println(controler.properties());
        shapes = new ArrayList<>();
        for(int i = 0; i < n; ++i) {
            double x = parent.width/2 + (radialOffset) * Math.sin(i * theta + angularOffset);
            double y = parent.height/2 + (radialOffset) * Math.cos(i * theta + angularOffset);
            Point pos = new Point(x,y);
            Circle c = new Circle(parent, pos);
            shapes.add(c);
        }
    }

    void update() {
        int n = 6;
        double theta = 2*Math.PI/n;
        int i = 0;
//        oscP5.OscEventListener listener;
//        controler.addListener(listener);
        //System.out.print("radialOffset:"+this.radialOffset+" - ");
        //System.out.println("angularOffset:"+this.angularOffset);
        for(Circle c: shapes) {

            //String str = String.format("%d, %s",i,c.toString());
            //System.out.println(str);

            double x = parent.width/2 + (radialOffset+100*radialOscilator.value()) * Math.sin(i * theta  + angularOffset + angularOscilator.value());
            double y = parent.height/2 + (radialOffset+100*radialOscilator.value())* Math.cos(i * theta  + angularOffset + angularOscilator.value());
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
    void oscEvent(OscMessage oscmsg) {
        String addr = oscmsg.addrPattern();
//        System.out.println(addr);
        // Uses the Automat5 controller of touchOsc
        //switch (addr) {
            // This is the left hand xy slider
          if(addr.equals("/3/xyM_l")) {
              float  lxval  = 300 * oscmsg.get(1).floatValue();
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
}
