import processing.core.PApplet;

class VirtLine {

    PApplet parent;
    float x1;
    float y1;
    float x2;
    float y2;

    VirtLine(PApplet theParent, float tempx1, float tempy1, float tempx2, float tempy2) {
        parent = theParent;
        x1 = tempx1;
        y1 = tempy1;
        x2 = tempx2;
        y2 = tempy2;
    }

    void display() {
        parent.line(x1, y1, x2, y2); // draw a line
    }

    void update() {
        x1 = parent.random(540);
        y1 = 0;
        x2 = parent.random(640);
        y2 = 360;
    }

    void debug() {
        parent.println("x1: " + x1 + ", y1: " + y1 + ", x2: ", + x2 + ", y2: " + y2);
    }

    void pointDebug() {
        parent.println("p1: (" + x1 + ", " + y1 + ") p2: (", + x2 + ", " + y2 +")");
    }

    void normalizeVector() {
        // could/ should return a normalized vector
    }

    // will move only orthogonaly to itself
    void moveOrth() {
        float v1 = x2 - x1;
        float v2 = y2 - y1;
        // solve for an orthoganal vector (a,b)
        float b = -v1 / v2;  //asumes "a" is 1

        // unitize the orthoganal vector
        float orthLen = parent.sqrt(parent.pow(1,2) + parent.pow(b,2));
        float orthU1 = 1/ orthLen;
        float orthU2 = b/ orthLen;

        // Move by the orthoganal
        x1 = x1 + orthU1;
        x2 = x2 + orthU1;
        y1 = y1 + orthU2;
        y2 = y2 + orthU2;
    }

    // Will move in a standard unifom direction
    void move() {
        // asume every line is going down and to the right at 45 degress
        x1 += 0.85;
        y1 += 0.85;
        x2 += 0.85;
        y2 += 0.85;

        // move right along the x
        //x1++;
        //x2++;
    }
}

