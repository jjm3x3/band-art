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


    public void settings() {
        fullScreen();
        //size(640, 360);
    }

    public void setup() {
        controler = new OscP5(this,12348);
        controler.status(0);
        System.out.println(controler.properties());


        Scene virtLineScene = new VirtLineScene(this);
        sceneMap.put(1, virtLineScene);
        Scene circleScene = new CircleScene(this);
        sceneMap.put(2,circleScene);
        Scene dotSequenceScene = new DotSequenceScene(this);
        sceneMap.put(0, dotSequenceScene);

    }

    public void draw() {
        if (sceneMap.containsKey(curScene)) {
            sceneMap.get(curScene).draw();
        }
    }

    public void keyPressed() {
        Scene theCurScene = sceneMap.get(curScene);
        if (theCurScene instanceof OscEventListener) { // if theCurScene == null it will never be an instance of OscEventListener
            controler.removeListener((OscEventListener) theCurScene);
        }

        // switch the scene by the numbers on the keyboard
        // implies that it is between 0 - 9
        if( key > '/' && key < ':' ) {
           try {
               curScene = Integer.parseInt(key+ "");
           } catch (NumberFormatException e) {
                System.out.println("how is this not a number: " + key);
           }
        }

        if (sceneMap.containsKey(curScene)) {
            Scene nextScene = sceneMap.get(curScene);
            if (nextScene instanceof OscEventListener) {
                controler.addListener((OscEventListener) nextScene);
            }
            nextScene.keyPressed(key);
        }
    }

}




