PImage img;
float[][] parameters = new float[10][6];
boolean[] selected = new boolean[10];
int temp[] = new int[3];
int distance_width = 10;
int distance_height = 50;

int w;
int h;

void setup() {
  size(1000, 600);
  image_setup();
  setparam(parameters);
}

void draw() {
  background(200);
  image(img, 0, 0);
  draw_counter();
  draw_switch();
  draw_sketches();
}

void mousePressed() {
  check_switch(parameters, selected, width/2 - 50, 500, 100, 50);
  mouseCheck(temp);
}

