import processing.core.*;

public class DotSequence {

    PApplet parent;

    float movingX, y;

    DotSequence(PApplet parent, int x, int y) {
        this.parent = parent;
        movingX = x;
        this.y = y;
    }

    void draw() {

        for (int i = 0; i < 20; ++i) {
            parent.stroke(255, 0 + i * 12);
            parent.point(movingX + i * 10, y);
        }

        movingX += 5;

        if (movingX >= 640) {
            movingX = 0;
        }
    }
}
