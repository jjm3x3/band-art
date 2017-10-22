import processing.core.PApplet;

public class DotSequenceScene {

    float movingX = 15;
    DotSequence[] sequences = new DotSequence[200];
    DotSequence seq1;
    DotSequence seq2;
    DotSequence seq3;
    PApplet parent;

    DotSequenceScene(PApplet parent) {
        this.parent = parent;
        
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
            sequences[i] = new DotSequence(parent, 5 * (i % 21) + horizontalOffset, 10 * (i - partitionOffset));
        }
        seq1 = new DotSequence(parent, 20, 15);
        seq2 = new DotSequence(parent, 15, 25);
        seq3 = new DotSequence(parent, 20, 35);
    }

    public void draw() {
        parent.background(0);

        parent.strokeWeight(10);

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

    public void keyPressed(char key) {
        if(key == 'r') {
            for(int i = 0; i < sequences.length; ++i) {
                sequences[i].addRandomColor();
            }
        }
    }
}
