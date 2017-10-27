import javafx.util.Pair;
import processing.core.PApplet;
import oscP5.*;
import processing.core.PShape;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CircleScene  implements Scene, oscP5.OscEventListener {

    PApplet parent;

    List<Circle> shapes;
    List<PVector> direction; // orientation
    List<Integer> pointNetIndices;

    PointNetwork pointNet;

    Float radialOffset;
    Float angularOffset;
    Oscilator radialOscilator;
    Oscilator angularOscilator;
    int backgroundColor = 0;

    PointNetwork purtPoints;

    CircleScene(PApplet parent, PointNetwork layout) {
        pointNet = layout;
        purtPoints = pointNet.randomPurturbation();
        this.parent = parent;
        radialOffset = (float) 0;
        angularOffset = (float) 0;
        radialOscilator = new Oscilator(parent);
        angularOscilator = new Oscilator(parent);
        setup();
    }

    public void setup() {
        parent.background(backgroundColor);
        shapes = new ArrayList<>();
        direction = new ArrayList<>();
        pointNetIndices = new ArrayList<>();
        float spacing = pointNet.meanSpacing();
        for(Map.Entry<Integer, Tuple<PVector,List<PVector>>> pair: pointNet.net.entrySet()) {
            int ind = pair.getKey();
            List<PVector> nbrs = pair.getValue().y;
            PVector basePoint = pair.getValue().x.copy();
            for(PVector nbrPoint: nbrs) {
                Circle c = new Circle(parent, basePoint, spacing);
                PVector dirbase = nbrPoint.copy();
                PVector dir = (dirbase.sub(basePoint)).normalize().mult(spacing);
                shapes.add(c);
                direction.add(dir);
                pointNetIndices.add(ind);
            }
        }
    }

    void shifter(Circle c, PVector dir, int ind) {
        PVector angularPurturbation = new PVector((float) Math.sin(angularOffset+angularOscilator.value()),(float) Math.cos(angularOffset+angularOscilator.value()));
        PVector radialPurturbation = dir.mult(radialOffset+radialOscilator.value());
        PVector basepos = pointNet.net.get(ind).x.copy();
        PVector pos = basepos.add(radialPurturbation.add(angularPurturbation.mult(1)));
        c.move(pos);
    }

//    void filler(Circle c, PVector dir, int ind) {
//        c.shape.setFill(true);
//        c.display();
//        superrepeter(c,dir,ind);
//    }

    void update() {
//        PVector angularPurturbation = new PVector((float) Math.sin(angularOffset+angularOscilator.value()),(float) Math.cos(angularOffset+angularOscilator.value()));
        parent.background(backgroundColor);
        for(Tuple<Circle,Tuple<Integer,PVector>> shapeState: Tuple.zipLists(shapes,Tuple.zipLists(pointNetIndices,direction))) {
            Circle c = shapeState.x;
            int ind = shapeState.y.x;
            PVector dir = shapeState.y.y.copy();
            shifter(c,dir,ind);
            //filler(c,dir,ind);
            //            PVector radialPurturbation = dir.mult(radialOffset+radialOscilator.value());
            //PVector basepos = pointNet.net.get(ind).x.copy();
            //PVector pos = basepos.add(radialPurturbation.add(angularPurturbation.mult(1)));
           //c.move(pos);

            //c.display();
        }
    }


    public void draw() {
        update();
//
//        for(Circle c: shapes) {
//
//        }
//    }
    }
    @Override
    public void keyPressed(char key) { }

    /* incoming osc message are forwarded to the oscEvent method. */
    // NB: the switching below appears to require if-thens.
    public void oscEvent(OscMessage oscmsg) {
        String addr = oscmsg.addrPattern();
        //        System.out.println(addr);
        // Uses the Automat5 controller of touchOsc
        // This is the left hand xy slider
        if(addr.equals("/3/xyM_l")) {
              float  lxval  = 10 * oscmsg.get(1).floatValue();
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
