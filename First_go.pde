    
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
  
  //firstLine.normalizeVector();
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
   
   void normalizeVector() {
     // could/ should return a normalized vector
   }
   
   void moveOrth() {
     float v1 = x2 - x1;
     float v2 = y2 - y1;
     //float vLen = sqrt(pow(v1, 2) + pow(v2, 2)); 
     //float u1 = v1 / vLen;
     //float u2 = v2 / vLen;
     float b = -v1 / v2;  //asumes "a" is 1
     
     //float zero = v1 * 1 + v2 * b;
     println("What are these vectors: (" +  v1 + ", " + v2 + "), (" + 1 + ", " + b + ")");
     //println("This should be 0: " + zero);
     float orthLen = sqrt(pow(1,2) + pow(b,2));
     float orthU1 = 1/ orthLen;
     float orthU2 = b/ orthLen;
     
     println("Here is an orth unit: (" + orthU1 + ", " + orthU2 + ")");
     x1 = x1 + orthU1;
     x2 = x2 + orthU1;
     y1 = y1 + orthU2;
     y2 = y2 + orthU2;
     // just calling normalizeVector to try it out
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