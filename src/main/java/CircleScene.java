import javafx.util.Pair;
import processing.core.PApplet;
import oscP5.*;
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

    oscP5.OscP5 controler;
    Float radialOffset;
    Float angularOffset;
//    TriangularLayout homeCenters;
    Oscilator radialOscilator;
    Oscilator angularOscilator;
    int backgroundColor = 0;
    CircleScene(PApplet parent) {
        this.parent = parent;
        radialOffset = (float) 0;
        angularOffset = (float) 0;
        radialOscilator = new Oscilator(parent);
        angularOscilator = new Oscilator(parent);
        setup();
    }

    public void setup() {
        parent.background(backgroundColor);
        int numRadialPoints = 6;
        pointNet = TriangularLayout.makeTriangularNet(parent.width/2,numRadialPoints,new PVector(parent.width/2,parent.height/2));

        //double theta = 2*Math.PI/numCircles;

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


    public void draw() {
        update();
        parent.background(backgroundColor);
        for(Circle c: shapes) {
            c.display();
        }
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
