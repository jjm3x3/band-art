import processing.core.PApplet;

public class Main extends PApplet {

    public static void main(String[] args) {
        PApplet.main("Main");
    }


    VirtLine firstLine;
    VirtLine secondLine;

    boolean isMoving = false;

    public void settings() {
        size(640, 360);
    }

    public void setup() {
        strokeWeight(10);
        stroke(0, 0);

        firstLine = new VirtLine(this,300, 0, 160, 360);
        firstLine.setShade(true);
        secondLine = new VirtLine(this,660, 0, 300, 360);
        secondLine.setShade(false);
    }

    public void draw() {

        // first frame of story bord
        //line(300, 0, 160, 360);
        //line(0, 100, 640, 300);
        //line(660, 0, 300, 360);

        background(150);

        if (isMoving) {

            //firstLine.move();
            //secondLine.move();

            firstLine.moveOrth();
            secondLine.moveOrth();
        }

        firstLine.display();
        secondLine.display();

    }

    public void keyPressed() {
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




