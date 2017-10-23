import processing.core.*;

public class VirtLineScene implements Scene {

    PApplet parent;

    VirtLine firstLine;
    VirtLine secondLine;

    boolean isMoving = false;

    VirtLineScene(PApplet parent) {
        this.parent = parent;

        firstLine = new VirtLine(parent, (float)(parent.width * (4.0/7)), 0, (float)(parent.width * (1.0/4)), parent.height);
        firstLine.setShade(true);
        secondLine = new VirtLine(parent, parent.width + (float)(parent.width * (1.0/30)), 0, (float)(parent.width * (2.0/5)), parent.height);
        secondLine.setShade(false);
    }

    @Override
    public void setup() { }

    public void draw(){

        // first frame of story bord
        //line(300, 0, 160, 360);
        //line(0, 100, 640, 300);
        //line(660, 0, 300, 360);

        parent.strokeWeight(10);
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

    public void keyPressed(char key) {
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
