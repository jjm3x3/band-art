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

        for (int i = 0; i < sequences.length; ++i) {
            int horizontalOffset = 0;
            int partitionOffset = 0;
            if(i > 100) {
                horizontalOffset = 400;
                partitionOffset = 100;
            } else if(i > 50) {
                horizontalOffset = 200;
                partitionOffset = 50;
            }
            sequences[i] = new DotSequence(this, 5 * (i % 21) + horizontalOffset, 10 * (i - partitionOffset));
        }
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

    DotSequence[] sequences = new DotSequence[200];
    DotSequence seq1;
    DotSequence seq2;
    DotSequence seq3;
    void dotScene() {
        background(0);

        strokeWeight(10);

        for(int i = 0; i < sequences.length; ++i)  {
            sequences[i].draw();
        }
//        seq1.draw();
//        seq2.draw();
//        seq3.draw();
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
        if(key == 'r') {
            for(int i = 0; i < sequences.length; ++i) {
                sequences[i].addRandomColor();
            }
        }
    }

}




