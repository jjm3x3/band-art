/**
 * Arm. 
 * 
 * The angle of each segment is controlled with the mouseX and
 * mouseY position. The transformations applied to the first segment
 * are also applied to the second segment because they are inside
 * the same pushMatrix() and popMatrix() group.
 */

float x, y;
float angle1 = 0.0;
float angle2 = 0.0;
float segLength = 100;

float x1 = 0;
    int y1 = 0;
float x2 = 0;
    int y2 = 0;

void setup() {
  size(640, 360);
  strokeWeight(10);
  stroke(255, 255);

  x = width * 0.3;
  y = height * 0.5;
}

void draw() {
  background(0);

  // arm example
  //angle1 = (mouseX/float(width) - 0.5) * -PI;
  //angle2 = (mouseY/float(height) - 0.5) * PI;
  //pushMatrix();
  //segment(x, y, angle1); 
  //segment(segLength, 0, angle2);
  //popMatrix();
  
  // first frae of story bord
  //line(300, 0, 160, 360);
  //line(0, 100, 640, 300);
  //line(660, 0, 300, 360);
  
  // Moving/scaling a line with moseX
  //line(2*mouseX, 0, mouseX, 360);

    background(204);
    
  //if (keyPressed == true && key == 'r') { // If the key is pressed,
  //  x1 = random(540);
  //  y1 = 0;
  //  x2 = random(640);
  //  y2 = 360;
    
  //  line(x1, y1, x2, y2); // draw a line
  //} 
    line(x1, y1, x2, y2); // draw a line
  //else { // Otherwise,
  //  rect(40, 40, 20, 20); // draw a rectangle
  //}
  
  // test func
  doThing();
}

class VirtLine {
  float x1;
  float y1;
  float x2;
  float y2;
   VirtLine(float tempx1, float tempy1, float tempx2, float tempy2) {
     
   }
}

void keyPressed() {
      x1 = random(540);
    y1 = 0;
    x2 = random(640);
    y2 = 360;
    
    line(x1, y1, x2, y2); // draw a line
}

void doThing() {
  int thing = 8;

  angle1 = (mouseX/float(thing) - 0.5) * -PI;
}

void segment(float x, float y, float a) {
  translate(x, y);
  rotate(a);
  line(0, 0, segLength, 0);
}