import processing.core.PApplet;

public class Main extends PApplet {

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    int curScene = 0;

    VirtLineScene scene1;
    CircleScene scene2;
    public void settings() {
        fullScreen();
        //size(640, 360);
    }

    public void setup() {
        scene1 = new VirtLineScene(this);
        scene2 = new CircleScene(this);
        seq1 = new DotSequence(this, 20, 15);
        seq2 = new DotSequence(this, 15, 25);
        seq3 = new DotSequence(this, 20, 35);
    }

    public void draw() {
        switch(curScene) {
            case 1:
                scene1.draw();
                return;
            case 2:
                dotScene();
                return;
            default:
                scene2.draw();
                //circles();
        }


    }


    int[] states = new int[6];


    float movingX = 15;
    DotSequence seq1;
    DotSequence seq2;
    DotSequence seq3;
    void dotScene() {
        background(0);

        strokeWeight(10);
//        stroke(255);
//        for (int i = 0; i < 20; ++i) {
//            stroke(255, 0 + i * 12);
//            point(movingX + i * 10, 25);
//            point(movingX + 5 + i * 10, 35);
//        }
//
//        movingX += 5;
//
//        if (movingX >= 640) {
//            movingX = 0;
//        }

        seq1.draw();
        seq2.draw();
        seq3.draw();
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
        scene1.keyPressed(key);
    }

}




