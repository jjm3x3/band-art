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
        stroke(0, 255);

        firstLine = new VirtLine(this, 300, 0, 160, 360);
        firstLine.setShade(true);
        secondLine = new VirtLine(this, 660, 0, 300, 360);
        secondLine.setShade(false);
    }

    float movingX = 15;

    int curScene = 0;
    public void draw() {
        switch(curScene) {
            case 1:
                firstScene();
                return;
            default:
                dotScene();
        }


    }

    void dotScene() {
        background(0);

        stroke(255);
        for (int i = 0; i < 20; ++i) {
            stroke(255, 0 + i * 12);
            point(movingX + i * 10, 25);
            point(movingX + 5 + i * 10, 35);
        }

        movingX += 5;

        if (movingX >= 640) {
            movingX = 0;
        }

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void keyPressed() {
        switch(key) {
            case '1':
                curScene = 1;
                return;
            case '0':
                curScene = 0;
                return;
        }
        if (key == 'r') {
            firstLine.update();
            secondLine.update();
        } else if (key == 'm') {
            firstLine.move();
        } else if (key == 'M') {
            isMoving = !isMoving;
        }
    }

    void firstScene(){

        // first frame of story bord
        //line(300, 0, 160, 360);
        //line(0, 100, 640, 300);
        //line(660, 0, 300, 360);

        background(150);

        if (isMoving) {

            //firstLine.move();
            //secondLine.move();

            firstLine.moveOrth(false);
            secondLine.moveOrth(true);
        }

        firstLine.display();
        secondLine.display();
    }
}




