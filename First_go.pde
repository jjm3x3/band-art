    
VirtLine firstLine;
VirtLine secondLine;

boolean isMoving = false;

void setup() {
  size(640, 360);
  strokeWeight(10);
  stroke(255, 255);

  firstLine = new VirtLine(300, 0, 160, 360);
  secondLine = new VirtLine(660, 0, 300, 360);
}

void draw() {
  
  // first frame of story bord
  //line(300, 0, 160, 360);
  //line(0, 100, 640, 300);
  //line(660, 0, 300, 360);

  background(0);
  
  if (isMoving) {
     firstLine.move();
     secondLine.move();
  }
  
  firstLine.display();
  secondLine.display();
  
  firstLine.pointDebug();
}

void keyPressed() {
  if (key == 'r') {
    firstLine.update();
    secondLine.update();
  } else if (key == 'm') {
     firstLine.move(); 
  } else if (key == 'M') {
    isMoving = !isMoving;
  }
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
   
   void debug() {
     println("x1: " + x1 + ", y1: " + y1 + ", x2: ", + x2 + ", y2: " + y2);
   }
   
   void pointDebug() {
      println("p1: (" + x1 + ", " + y1 + ") p2: (", + x2 + ", " + y2 +")");
   }
   
   void move() {
     // asume every line is going down and to the right at 45 degress
     x1 += 0.85;
     y1 += 0.85;
     x2 += 0.85;
     y2 += 0.85;
   
     // move right along the x
     //x1++;
     //x2++;
   }
}