import oscP5.OscP5;
import processing.core.PApplet;

public class Main extends PApplet {

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    oscP5.OscP5 controler;
    int curScene = 0;

    VirtLineScene virtLineScene;
    CircleScene circleScene;
    DotSequenceScene dotSequenceScene;

    public void settings() {
        fullScreen();
        //size(640, 360);
    }

    public void setup() {
        controler = new OscP5(this,12348);
        controler.status(0);
        System.out.println(controler.properties());

        virtLineScene = new VirtLineScene(this);
        circleScene = new CircleScene(this);
        controler.addListener(circleScene);
        dotSequenceScene = new DotSequenceScene(this);

    }

    public void draw() {
        switch(curScene) {
            case 1:
                virtLineScene.draw();
                return;
            case 2:
                circleScene.draw();
                return;
            default:
                dotSequenceScene.draw();
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




