void sketch(int x, int y, float w, float h, float k_r, float k_g, float k_b, float b_r, float b_g, float b_b) {
  for (int i = 0; i< w; i++) {
    for (int j = 0; j < h; j++) {
      color c = get(i, j);
      float preR = red(c);
      float preG = green(c);
      float preB = blue(c);

      float r = 1 / 30;

      float g_r = 255 / (1 + exp(-1 * r * (preR-127.5)));
      float g_g = 255 / (1 + exp(-1 * r * (preG-127.5)));
      float g_b = 255 / (1 + exp(-1 * r * (preB-127.5)));

      int R = (int) (k_r * (preR + b_r * (g_r - preR)));
      int G = (int) (k_g * (preG + b_g * (g_g - preG)));
      int B = (int) (k_b * (preB + b_b * (g_b - preB)));

      check(R, G, B);
      set( x+i, y+j, color(R, G, B));
    }
  }
}

void draw_counter() {
  fill(0);
  textSize(20);
  int count=0;
  for (int l=0; l<temp.length; l++) {
    if (temp[l] != 0) {
      count++;
    }
  }
  text(count + " selects", w+10, 30);
}

void draw_switch() {
  fill(255);
  rect(width/2 - 50, 500, 100, 50);
}

void draw_selecter(boolean[] s, int t, int u, int v) {
  if (s[t]) {
    noFill();
    strokeWeight(5);
    stroke(50, 50, 255);
    rect((w + distance_width) * (v+1) -3, (h + distance_height) * (u+1) -3, w+3, h+3);
  }
}

void draw_sketches() {
  for (int i=0; i<2; i++) {
    for (int j=0; j<5; j++) {
      int k = i*5 + j;
      draw_selecter(selected, k, i, j);
      sketch((w + distance_width) * (j+1), (h + distance_height) * (i+1), w, h, parameters[k][0], parameters[k][1], parameters[k][2], parameters[k][3], parameters[k][4], parameters[k][5]);
    }
  }
}

void image_setup() {
  img = loadImage("sample1.jpg");
  img.resize(img.width/8, img.height/8);
  w = img.width;
  h = img.height;
}

