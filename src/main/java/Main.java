import oscP5.OscEventListener;
import oscP5.OscP5;
import processing.core.PApplet;

import java.util.HashMap;
import java.util.Map;

public class Main extends PApplet {

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    oscP5.OscP5 controler;
    int curScene = 0;


    Map<Integer, Scene> sceneMap = new HashMap<>();

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
        sceneMap.put(1, virtLineScene);
        circleScene = new CircleScene(this);
        sceneMap.put(2,circleScene);
        dotSequenceScene = new DotSequenceScene(this);
        sceneMap.put(0, dotSequenceScene);

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
        Scene theCurScene = sceneMap.get(curScene);
        if (theCurScene instanceof OscEventListener) {
            controler.removeListener((OscEventListener) theCurScene);
        }
        switch(key) {
            case '0':
                curScene = 0;
                break;
            case '1':
                curScene = 1;
                break;
            case '2':
                curScene = 2;
                break;
                
        }
        Scene nextScene = sceneMap.get(curScene);
        if (nextScene instanceof OscEventListener) {
            controler.addListener((OscEventListener) nextScene);
        }
        virtLineScene.keyPressed(key);
        dotSequenceScene.keyPressed(key);
    }

}




