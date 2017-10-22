import processing.core.PApplet;

public class Main extends PApplet {

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    int curScene = 0;

    VirtLineScene virtLineScene;
    CircleScene circleScene;
    DotSequenceScene dotSequenceScene;

    public void settings() {
        fullScreen();
        //size(640, 360);
    }

    public void setup() {
        virtLineScene = new VirtLineScene(this);
        circleScene = new CircleScene(this);
        dotSequenceScene = new DotSequenceScene(this);

    }

    public void draw() {
        switch(curScene) {
            case 1:
                virtLineScene.draw();
                return;
            case 2:
                dotSequenceScene.draw();
                return;
            default:
                circleScene.draw();
                //circles();
        }


    }

    public void keyPressed() {
        switch(key) {
            case '0':
                curScene = 0;
                return;
            case '1':
                curScene = 1;
                return;
            case '2':
                curScene = 2;
                return;
                
        }
        virtLineScene.keyPressed(key);
        dotSequenceScene.keyPressed(key);
    }

}




