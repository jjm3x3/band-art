import processing.core.*;

public class VirtLineScene {

    PApplet parent;

    VirtLine firstLine;
    VirtLine secondLine;

    boolean isMoving = false;

    VirtLineScene(PApplet parent) {
        this.parent = parent;

        firstLine = new VirtLine(parent, 300, 0, 160, 360);
        firstLine.setShade(true);
        secondLine = new VirtLine(parent, 660, 0, 300, 360);
        secondLine.setShade(false);
    }

    void draw(){

        // first frame of story bord
        //line(300, 0, 160, 360);
        //line(0, 100, 640, 300);
        //line(660, 0, 300, 360);

        parent.background(150);

        if (isMoving) {

            //firstLine.move();
            //secondLine.move();

            firstLine.moveOrth(false);
            secondLine.moveOrth(true);
        }

        firstLine.display();
        secondLine.display();
    }

    void keyPressed(char key) {
         if (key == 'r') {
            firstLine.update();
            secondLine.update();
        } else if (key == 'm') {
            firstLine.move();
        } else if (key == 'M') {
            isMoving = !isMoving;
        }
    }
}
