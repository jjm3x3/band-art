import javafx.util.Pair;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// This structure organizes a distribution of points along with groups of associated directions:
public class PointNetwork {
    Map<Integer,Tuple<PVector,List<PVector>>> net;
    PointNetwork() {
        net = new HashMap<>();
    }
    float meanSpacing() {
        float space = 0;
        float count = 0;
        for(Map.Entry<Integer, Tuple<PVector,List<PVector>>> pair: net.entrySet()) {
            List<PVector> nbrs = pair.getValue().y;
            PVector basePoint = pair.getValue().x.copy();
            for(PVector nbr: nbrs) {
                PVector nbrcopy = nbr.copy();
                space = (1/(count+1))*(count*space + nbrcopy.dist(basePoint));
                count++;
            }
        }
        return space;
    }
    Tuple<PVector,PVector> extremalPoint() {
        float xmin = 100000;
        float ymin = 100000;
        float xmax = 0;
        float ymax = 0;
        for(Tuple<PVector,List<PVector>> pair: this.net.values()) {
            xmin = Math.min(xmin,pair.x.x);
            ymin = Math.min(ymin,pair.x.y);
            xmax = Math.max(xmax,pair.x.x);
            ymax = Math.max(ymax,pair.x.y);
        }
        PVector minPoint = new PVector(xmin,ymin);
        PVector maxPoint = new PVector(xmax,ymax);
        return new Tuple<>(minPoint,maxPoint);
    }
    PointNetwork randomPurturbation() {
        Tuple<PVector,PVector> extPair = this.extremalPoint();
        PVector minPoint = extPair.x;
        PVector maxPoint = extPair.y;
        float w = maxPoint.x -  minPoint.x;
        float h = maxPoint.y - minPoint.y;
        PointNetwork net = new PointNetwork();
        for(Map.Entry<Integer, Tuple<PVector,List<PVector>>> pair: this.net.entrySet()) {
            int key = pair.getKey();
            double x = w * Math.random() + minPoint.x;
            double y = h * Math.random() + minPoint.y;
            PVector point = new PVector((float) x, (float) y);
            List<PVector> nbrlist = new ArrayList<>();
            for( PVector pnt: pair.getValue().y) {
                double nx = Math.random();
                double ny = Math.random();
                PVector nbr = new PVector((float) nx, (float) ny);
                nbrlist.add(nbr.copy());
            }
            net.net.put(key, new Tuple(point, nbrlist));
        }
    return net;
    }
}

