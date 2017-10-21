import processing.core.PApplet;

public class Main extends PApplet {

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    int curScene = 0;

    VirtLineScene scene1;

    public void settings() {
        size(640, 360);
    }

    public void setup() {
        scene1 = new VirtLineScene(this);

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
                circles();
        }


    }


    int[] states = new int[6];
    void circles() {
        if (states[0] == 0) {
            for(int i = 0; i < states.length; ++i) {
                states[i] = 50;
            }
        } else {
            for(int i = 0; i < states.length; ++i) {
                states[i]++;
            }
        }
        int circleSize = 50;
        fill(200,10,200);
        for (int i = 0; i < states.length; ++i) {
            ellipse(states[i],50, circleSize, circleSize);
        }
//        ellipse(50,50, circleSize, circleSize);
//        ellipse(50,50, circleSize, circleSize);
//        ellipse(50,50, circleSize, circleSize);
//        ellipse(50,50, circleSize, circleSize);
//        ellipse(50,50, circleSize, circleSize);
//        ellipse(50,50, circleSize, circleSize);
    }




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




