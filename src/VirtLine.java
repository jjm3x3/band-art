import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;

class VirtLine {

    PApplet parent;
    float x1;
    float y1;
    float x2;
    float y2;

    boolean isShading;
    boolean left;
    int color;

    VirtLine(PApplet theParent, float tempx1, float tempy1, float tempx2, float tempy2) {
        parent = theParent;
        x1 = tempx1;
        y1 = tempy1;
        x2 = tempx2;
        y2 = tempy2;

        isShading = false;
        color = parent.color(0, 0, 255, 220);
    }

    void display() {
        if (isShading) {
            PShape shade = parent.createShape();
            shade.beginShape();
            shade.fill(color);
            shade.noStroke();
            if (left) {
                shade.vertex(0, y1);
                shade.vertex(x1, y1);
                shade.vertex(x2, y2);
                shade.vertex(0, y2);
            } else {
                shade.vertex(x1, y1);
                shade.vertex(640, y1);
                shade.vertex(640, y2);
                shade.vertex(x2, y2);
            }
            shade.endShape(PConstants.CLOSE);
            parent.shape(shade);
        }
        parent.line(x1, y1, x2, y2); // draw a line
    }

    void update() {
        x1 = parent.random(540);
        y1 = 0;
        x2 = parent.random(640);
        y2 = 360;

        color = parent.color(parent.random(255),parent.random(255),parent.random(255), 200);
    }

    void setShade(boolean left) {
        isShading = !isShading;
        this.left = left;
    }

    // debug will print the four float values which
    // represent the two points of this object
    void debug() {
        parent.println("x1: " + x1 + ", y1: " + y1 + ", x2: ", + x2 + ", y2: " + y2);
    }

    // pointDebug prints out the four float values
    // in a pretty point looking format
    void pointDebug() {
        parent.println("p1: (" + x1 + ", " + y1 + ") p2: (", + x2 + ", " + y2 +")");
    }

    void normalizeVector() {
        // could/ should return a normalized vector
    }

    // will move only orthogonally to itself
    // if left is true it will move leftward
    void moveOrth() {
        float v1 = x2 - x1;
        float v2 = y2 - y1;
        // solve for an orthogonal vector (a,b)
        float b = -v1 / v2;  //assumes "a" is 1

        // unitize the orthogonal vector
        float orthLen = parent.sqrt(parent.pow(1,2) + parent.pow(b,2));
        float orthU1 = 1/ orthLen;
        float orthU2 = b/ orthLen;

        // Move by the orthoganal
        x1 = x1 + orthU1;
        x2 = x2 + orthU1;
        y1 = y1 + orthU2;
        y2 = y2 + orthU2;
    }

    // Will move in a standard uniform direction
    void move() {
        // assume every line is going down and to the right at 45 degrees
        x1 += 0.85;
        y1 += 0.85;
        x2 += 0.85;
        y2 += 0.85;

        // move right along the x
        //x1++;
        //x2++;
    }
}

