import processing.core.PApplet;
import processing.core.PVector;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO (fprac): What is this?
public class CircularLayout {


    float radius;
    int numRadialPoints;
    PVector center;

    private CircularLayout(float radius, int numRadialPoints, PVector center) {
        this.radius = radius;
        this.center = center;
        this.numRadialPoints = numRadialPoints;
        setup();
    }

    static PointNetwork makeCircularNet(float radius, int numRadialPoints, PVector center) {
        CircularLayout layout = new CircularLayout(radius, numRadialPoints, center);
        return layout.setup();
    }

    PointNetwork setup() {

        double theta = 2 * Math.PI / numRadialPoints;
        PointNetwork net = new PointNetwork();

        for (int i = 0; i < numRadialPoints; i++) {
            double x = center.x + radius * Math.sin(i * theta);
            double y = center.y + radius * Math.cos(i * theta);
            PVector point = new PVector((float) x, (float) y);
            double nx = center.x + radius * Math.sin((i + 1) * theta);
            double ny = center.y + radius * Math.cos((i + 1) * theta);
            List<PVector> nbrlist = new ArrayList<>();
            PVector nbr = new PVector((float) nx, (float) ny);
            nbrlist.add(nbr.copy());
            net.net.put(i, new Tuple(point, nbrlist));
        }
        //System.out.println(net.net);
        return net;
    }
}

