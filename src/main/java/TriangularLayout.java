import processing.core.PApplet;
import processing.core.PVector;
import java.util.ArrayList;
import java.util.List;

public class TriangularLayout {

    PApplet parent;
    float spacing;
    int numRadialPoints = 6;
    List<Point> points;

    float alpha = (float)Math.sqrt(3)/2;
    float beta = 1/2;

    TriangularLayout(PApplet parent) {
        this.parent = parent;
        setup();
    }

    void setup() {
        // just use the width to set the scale at the moment:
        float scale = parent.height/2;///(2*numRadialPoints);
        spacing = scale/numRadialPoints;

        points = new ArrayList<>();
        int numrows = 2*numRadialPoints+1;
        for(int i = 1; i<= numrows; ++i) {
            for (int j = 1; j <= numVertsInRow(i); ++j) {
                PVector point = ind2coords(i,j);
                points.add(new Point(point.x,point.y));
            }
        }
    }

    PVector ind2coords(int i, int j) {
        PVector initVector = new PVector(-3*alpha, beta).mult(spacing* numRadialPoints);
        PVector ur = new PVector(alpha, beta).mult(spacing);
        PVector d = new PVector((float)0,-2*beta).mult(spacing);
        PVector dr = new PVector(alpha,-beta).mult(spacing);
        if(i<=numRadialPoints+1) {
            return initVector.add(d.mult(i-1)).add(ur.mult(j-1));
        } else {
            return initVector.add(d.mult(numRadialPoints)).add(dr.mult(i-numRadialPoints-1)).add(ur.mult(j-1));
        }
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
