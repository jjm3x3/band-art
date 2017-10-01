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
  
  // first frame of story bord
  //line(300, 0, 160, 360);
  //line(0, 100, 640, 300);
  //line(660, 0, 300, 360);

  background(204);
  
  line(x1, y1, x2, y2); // draw a line
  
}

class VirtLine {
  float x1;
  float y1;
  float x2;
  float y2;
   VirtLine(float tempx1, float tempy1, float tempx2, float tempy2) {
     x1 = tempx1;
     y1 = tempy1;
     x2 = tempx2;
     y2 = tempy2;
   }
   
   void display() {
    line(x1, y1, x2, y2); // draw a line   
   }
}

void keyPressed() {
    x1 = random(540);
    y1 = 0;
    x2 = random(640);
    y2 = 360;
}