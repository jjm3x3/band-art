import processing.core.*;

public class DotSequence {

    PApplet parent;

    float movingX, y;
    int color;

    DotSequence(PApplet parent, int x, int y) {
        this.parent = parent;
        movingX = x;
        this.y = y;
    }

    void draw() {


        for (int i = 0; i < 20; ++i) {
            if(color != 0)  {
                parent.stroke(color, 0 + i * 12);
            } else {
                parent.stroke(255, 0 + i * 12);
            }
            parent.point(movingX + i * 10, y);
        }

        movingX += 10;

        if (movingX >= 640) {
            movingX = 0;
        }
    }

    void addRandomColor() {
        color = parent.color(parent.random(255),parent.random(255),parent.random(255), 200);
    }
}
