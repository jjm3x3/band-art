import processing.core.PApplet;
import processing.core.PVector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// TODO (fprac): What is this?
public class TriangularLayout extends PointNetwork {

    PApplet parent;
    float spacing;
    int numRadialPoints = 4;
    //List<Point> points;


    float alpha = (float)Math.sqrt(3)/2;
    float beta = (float)0.5;
    PVector center;
    TriangularLayout(PApplet parent) {
        this.parent = parent;
        setup();
    }

    void setup() {
        // just use the width to set the scale at the moment:
        float scale = parent.height/(numRadialPoints/2);///(2*numRadialPoints);
        spacing = scale/numRadialPoints;
        int numrows = 2*numRadialPoints+1;
        net = new HashMap<>();

        int pointInd = 0;
        for(int i = 1; i<= numrows; ++i) {
            for (int j = 1; j <= numVertsInRow(i); ++j) {
                PVector p = ind2coords(i,j);
                List<PVector> nbrs = vertNeighbors(i,j);
                Tuple<PVector,List<PVector>> vecNbrs = new Tuple<>(p,nbrs);
                net.put(pointInd,vecNbrs);
                pointInd++;
            }
        }
    }

    PVector ind2coords(int i, int j) {
        PVector  center = new PVector(parent.width/2,parent.height/2);
        PVector initVector = new PVector(-1*alpha, beta).mult(spacing* numRadialPoints);
        PVector ur = new PVector(alpha, beta).mult(spacing);
        PVector d = new PVector((float)0,-2*beta).mult(spacing);
        PVector dr = new PVector(alpha,-beta).mult(spacing);
        if(i<=numRadialPoints+1) {
            return initVector.add(d.mult(i-1)).add(ur.mult(j-1)).add(center);
        } else {
            return initVector.add(d.mult(numRadialPoints)).add(dr.mult(i-numRadialPoints-1)).add(ur.mult(j-1)).add(center);
        }
    }

    boolean isInterior(int i, int j) {
        if(i==1 || i == 2*numRadialPoints+1 || j ==1 || j==numVertsInRow(i)) {
            return false;
        } else {
            return true;
        }
    }

    List<PVector> vertNeighbors(int i, int j) {
        List<PVector> neighborPoints = new ArrayList<>();
        if(isInterior(i,j)) {
            if(i<=numRadialPoints+1) {
                neighborPoints.add(ind2coords(i-1,j-1));
                neighborPoints.add(ind2coords(i-1,j));
            } else {
                neighborPoints.add(ind2coords(i-1,j));
                neighborPoints.add(ind2coords(i-1,j+1));
            }
            neighborPoints.add(ind2coords(i,j-1));
            neighborPoints.add(ind2coords(i,j+1));
            if( i < numRadialPoints + 1) {
                neighborPoints.add(ind2coords(i+1,j));
                neighborPoints.add(ind2coords(i+1,j+1));
            } else {
                neighborPoints.add(ind2coords(i+1,j-1));
                neighborPoints.add(ind2coords(i+1,j));
            }
        }
        return neighborPoints;
    }

    // There are 2*numRadialPoints edges at the boundary of the hexagon
    int nv(int i) {
        return i + numRadialPoints;
    }
    int numVertsInRow(int i) {
        if(i <= numRadialPoints+1) {
            return nv(i);
        } else {
            return nv(c(i,numRadialPoints+1));
        }
    }

    int c(int i, int N) {
        if(i > N) {
            return 2*N-i;
        } else {
            return i;
        }
    }


}
