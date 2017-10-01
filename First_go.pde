

float x1 = 0;
    int y1 = 0;
float x2 = 0;
    int y2 = 0;
    
VirtLine firstLine;

void setup() {
  size(640, 360);
  strokeWeight(10);
  stroke(255, 255);

  firstLine = new VirtLine(x1,y1,x2,y2);
}

void draw() {
  
  // first frame of story bord
  //line(300, 0, 160, 360);
  //line(0, 100, 640, 300);
  //line(660, 0, 300, 360);

  background(204);
  firstLine.display();
  
  line(x1, y1, x2, y2); // draw a line
  
}

void keyPressed() {
    x1 = random(540);
    y1 = 0;
    x2 = random(640);
    y2 = 360;
    
    firstLine.update();
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
   
   void update() {
     x1 = random(540);
     y1 = 0;
     x2 = random(640);
     y2 = 360;
   }
}