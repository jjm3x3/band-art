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
            default:
                dotScene();
        }


    }

    float movingX = 15;
    DotSequence seq1;
    DotSequence seq2;
    DotSequence seq3;
    void dotScene() {
        background(0);

        strokeWeight(10);

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




