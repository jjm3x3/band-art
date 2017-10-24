import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

import java.util.List;

public class ShapeGroup {
    List<PShape> shapes;
    List<Integer> pointNetIndices;
    List<PVector> direction; // orientation
    PointNetwork pointNet;

    ShapeGroup(PApplet parent, PShape seedShape, PointNetwork pointNet) {

    }
}
