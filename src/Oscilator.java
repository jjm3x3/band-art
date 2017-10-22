import oscP5.*;
import processing.core.PApplet;

public class Oscilator {

    PApplet parent;
    Double frequency;
    int time0;

    Oscilator(PApplet parent) {
        this.parent = parent;
        setup();
    }

    public void setup() {
        time0 = parent.millis();
        frequency = (double) 0;
    }

    public float value() {
        double now = ((double)(parent.millis()-time0)%20000)/1000;
        return (float)Math.sin(frequency * 2 * Math.PI * now);
    }
}
