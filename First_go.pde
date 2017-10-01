    
VirtLine firstLine;
VirtLine secondLine;

void setup() {
  size(640, 360);
  strokeWeight(10);
  stroke(255, 255);

  firstLine = new VirtLine(0,0,0,0);
  secondLine = new VirtLine(0,0,0,0);
}

void draw() {
  
  // first frame of story bord
  //line(300, 0, 160, 360);
  //line(0, 100, 640, 300);
  //line(660, 0, 300, 360);

  background(204);
  firstLine.display();
  secondLine.display();
}

void keyPressed() {   
    firstLine.update();
    secondLine.update();
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