import processing.core.PApplet;

public class Main extends PApplet {

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    int curScene = 0;

    VirtLineScene scene1;
    CircleScene scene2;
    DotSequenceScene scene3;

    public void settings() {
        fullScreen();
        //size(640, 360);
    }

    public void setup() {
        scene1 = new VirtLineScene(this);
        scene2 = new CircleScene(this);
        scene3 = new DotSequenceScene(this);

    }

    public void draw() {
        switch(curScene) {
            case 1:
                scene1.draw();
                return;
            case 2:
                scene2.draw();
                return;
            default:
                scene3.draw();
                //circles();
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
            case '2':
                curScene = 2;
                return;
                
        }
        scene1.keyPressed(key);
        scene3.keyPressed(key);
    }

}




