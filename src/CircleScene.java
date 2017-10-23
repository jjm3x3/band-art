import javafx.util.Pair;
import processing.core.PApplet;
import oscP5.*;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CircleScene {

    PApplet parent;
    List<Circle> shapes;
    List<PVector> direction;
    List<Integer> pointNetIndices;
    PointNetwork pointNet;
    oscP5.OscP5 controler;
    Float radialOffset;
    Float angularOffset;
//    TriangularLayout homeCenters;
    Oscilator radialOscilator;
    Oscilator angularOscilator;
    int backgroundColor = 0;
    CircleScene(PApplet parent) {
        this.parent = parent;
        setup();
    }

    void setup() {
        parent.background(backgroundColor);
        TriangularLayout homeCenters = new TriangularLayout(parent);
        pointNet = new PointNetwork();
        pointNet.net = homeCenters.net;

        //double theta = 2*Math.PI/numCircles;

        radialOffset = (float) 0;
        angularOffset = (float) 0;
        oscP5.OscP5 controler = new OscP5(this,12348);
        radialOscilator = new Oscilator(parent);
        angularOscilator = new Oscilator(parent);
        //controler.status(0);
        //System.out.println(controler.properties());
        shapes = new ArrayList<>();
        direction = new ArrayList<>();
        pointNetIndices = new ArrayList<>();
        for(Map.Entry<Integer, Tuple<PVector,List<PVector>>> pair: pointNet.net.entrySet()) {
            int ind = pair.getKey();
            List<PVector> nbrs = pair.getValue().y;
            PVector basePoint = pair.getValue().x.copy();
            for(PVector nbrPoint: nbrs) {
                Circle c = new Circle(parent, basePoint, homeCenters.spacing);
                PVector dirbase = nbrPoint.copy();
                PVector dir = (dirbase.sub(basePoint)).normalize().mult(homeCenters.spacing);
                shapes.add(c);
                direction.add(dir);
                pointNetIndices.add(ind);
            }
        }


//        for(Pair<PVector,List<PVector>> pntNbrs: PointNetwork.zipLists(homeCenters.points,homeCenters.neighbors)) {
//            PVector p = pntNbrs.getKey();
//            List<PVector> nbrs = pntNbrs.getValue();
//            for(PVector np: nbrs) {
             //   double x = parent.width/2 + (radialOffset) * Math.sin(i * theta + angularOffset);
              //  double y = parent.height/2 + (radialOffset) * Math.cos(i * theta + angularOffset);


//            }



    }

    void update() {
//        double theta = 2*Math.PI/numCircles;
//        int i = 0;
////        oscP5.OscEventListener listener;
////        controler.addListener(listener);
//        //System.out.print("radialOffset:"+this.radialOffset+" - ");
//        //System.out.println("angularOffset:"+this.angularOffset);
        PVector angularPurturbation = new PVector((float) Math.sin(angularOffset+angularOscilator.value()),(float) Math.cos(angularOffset+angularOscilator.value()));

        for(Tuple<Circle,Tuple<Integer,PVector>> shapeState: Tuple.zipLists(shapes,Tuple.zipLists(pointNetIndices,direction))) {
            Circle c = shapeState.x;
            int ind = shapeState.y.x;
            PVector dir = shapeState.y.y.copy();
//            System.out.println("dir x:"+dir.x+" y:"+dir.y);
            PVector radialPurturbation = dir.mult(radialOffset+radialOscilator.value());
            PVector basepos = pointNet.net.get(ind).x.copy();
            PVector pos = basepos.add(radialPurturbation.add(angularPurturbation.mult(1)));
//            double x = pointNet.pointnet.get(ind).x + (radialOffset+100*) *;
//            double y = parent.height/2 + (radialOffset+100*radialOscilator.value())* Math.cos(i * theta  + angularOffset + angularOscilator.value());
////            double x = parent.width/2 + (radialOffset * radialOscilator.value()) * Math.sin(i * theta  + angularOffset * angularOscilator.value());
////            double y = parent.height/2 + (radialOffset * radialOscilator.value())* Math.cos(i * theta  + angularOffset * angularOscilator.value());
//            i++;
//            System.out.print("initialpos:x:"+c.center.x+" y:"+c.center.y);
//            System.out.println(" goesto pos:x:"+pos.x+" y:"+pos.y);
           c.move(pos);
        }
    }

    void draw() {
        update();
        parent.background(backgroundColor);
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
}
