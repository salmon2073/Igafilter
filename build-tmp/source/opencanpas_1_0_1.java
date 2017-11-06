import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class opencanpas_1_0_1 extends PApplet {

PImage img;
float[][] parameters = new float[10][6];
boolean[] selected = new boolean[10];
int temp[] = new int[3];
int distance_width = 10;
int distance_height = 50;

int w;
int h;

public void setup() {
  size(1000, 600);
  image_setup();
  setparam(parameters);
}

public void draw() {
  background(200);
  image(img, 0, 0);
  draw_counter();
  draw_switch();
  draw_sketches();
}

public void mousePressed() {
  check_switch(parameters, selected, width/2 - 50, 500, 100, 50);
  mouseCheck(temp);
}

public void check(int R, int G, int B) {
  if (R > 255) {
    R = 255;
  }
  if (G > 255) {
    G = 255;
  }
  if (B > 255) {
    B = 255;
  }

  if (R < 0) {
    R = 0;
  }
  if (G < 0) {
    G = 0;
  }
  if (B < 0) {
    B = 0;
  }
}

public void check_switch(float p[][], boolean b[], int x, int y, int w, int h) {
  if (mouseX >= x && mouseX <= x+w && mouseY >= y && mouseY <= y+h ) {
    setparam(p, temp[0], temp[1], temp[2]);
    initTemp();
    for (int i=0; i < 10; i++) {
      b[i] = false;
    }
  }
}

public void mouseCheck(int[] temp) {
  for (int i=0; i<2; i++) {
    for (int j=0; j<5; j++) {
      int s = (w + distance_width) * (j+1);
      int t = (w + distance_width) * (j+1) + w;
      int u = (h + distance_height) * (i+1);
      int v = (h + distance_height) * (i+1) + h;

      if (mouseX >= s && mouseX <= t && mouseY >= u && mouseY <= v) {
        setTemp(temp, i*5 + j);
      }
    }
  }
}

public void setTemp(int[] temp, int k) {
  if (selected[k]) {
    selected[k] = false;
    delete_selected(temp, k);
  }
  else {
    selected[k] = true;
    set_selected(temp, k);
  }
}

public void initTemp() {
  for (int i=0; i<temp.length; i++) {
    temp[i] =0;
  }
}

public void delete_selected(int[] temp, int k) {
  for (int i=0; i<temp.length; i++) {
    if (temp[i] == k) {
      temp[i] = 0;
    }
  }
}


public void set_selected(int[] temp, int k) {
  for (int i=0; i<temp.length; i++) {
    if (temp[i] == 0) {
      temp[i] =k;
      break;
    }
  }
}

public void setparam(float parameters[][]) {
  for (int i=0; i<parameters.length;i++ ) {
    for (int j=0; j<parameters[0].length;j++) {
      parameters[i][j] = random(1);
    }
  }
}


public void setparam(float parameters[][], int p1, int p2, int p3) {

  if (p1 == p2 || p2 == p3 || p3 == p1) {
    return;
  }

  //crossing part
  int t1, t2, t3, t4;
  int u1, u2, u3, u4;
  int[] n = {
    p1, p2, p3
  };

  while (true) {
    t1 = (int)random(3);
    t2 = (int)random(3);
    if (t1 != t2) {
      break;
    }
  }

  while (true) {
    t3 = (int)random(3);
    t4 = (int)random(3);
    if (t1 == t3 && t2 == t4) {
    }
    else if (t2 == t3 && t1 == t4) {
    }
    else {
      if (t3 != t4) {
        break;
      }
    }
  }

  while (true) {
    u1 = (int)random(10);
    u2 = (int)random(10);
    u3 = (int)random(10);
    u4 = (int)random(10);

    if (u1 != u2 && u1 != u3 && u1 != u4 && u2 != u3 && u2 != u4 && u3 != u4 &&
      u1 != p1 && u1 != p2 && u1 != p3 &&
      u2 != p1 && u2 != p2 && u2 != p3 &&
      u3 != p1 && u3 != p2 && u3 != p3 &&
      u4 != p1 && u4 != p2 && u4 != p3) {  
      break;
    }
  }

  crossing(parameters[n[t1]], parameters[n[t2]], parameters[u1], parameters[u2]);
  crossing(parameters[n[t3]], parameters[n[t4]], parameters[u3], parameters[u4]);


  //mutation part
  int s1, s2, s3;
  int v1, v2, v3;
  int[] m = {
    p1, p2, p3, u1, u2, u3, u4
  };

  int[] last = new int[3];


  while (true) {
    s1 = (int)random(7);
    s2 = (int)random(7);
    s3 = (int)random(7);
    if (s1 != s2 && s1 != s3 && s2 != s3) {
      break;
    }
  }

  int t=0;
  for (int i = 0; i<parameters.length; i++) {
    if (i != p1 && i != p2 &&i != p3 && i != u1 && i != u2 && i != u3 && i != u4) {
      last[t] = i;
      t++;
    }
  }

  mutation(m[s1], m[s2], m[s3], parameters[last[0]], parameters[last[1]], parameters[last[2]]);
}


public void crossing(float a[], float b[], float p1[], float p2[]) {
  int bit = (int)(random(p1.length));
  for (int i=0; i < p1.length; i++) {
    if (i > bit) {
      p1[i] = a[i];
    }
    else {
      p1[i] = b[i];
    }
  }

  for (int i=0; i < p2.length; i++) {
    if (i < bit) {
      p2[i] = a[i];
    }
    else {
      p2[i] = b[i];
    }
  }
}


public void mutation(int s1, int s2, int s3, float p1[], float p2[], float p3[]) {
  int bit = (int)(random(p1.length));
  for (int j=0; j<p1.length;j++) {
    if (j == bit) {  
      p1[j] = 1-p1[j];
    }
  }

  bit = (int)(random(p2.length));
  for (int j=0; j<p2.length;j++) {
    if (j == bit) {  
      p2[j] = 1-p2[j];
    }
  }

  bit = (int)(random(p3.length));
  for (int j=0; j<p3.length;j++) {
    if (j == bit) {  
      p3[j] = 1-p3[j];
    }
  }
}

public void sketch(int x, int y, float w, float h, float k_r, float k_g, float k_b, float b_r, float b_g, float b_b) {
  for (int i = 0; i< w; i++) {
    for (int j = 0; j < h; j++) {
      int c = get(i, j);
      float preR = red(c);
      float preG = green(c);
      float preB = blue(c);

      float r = 1 / 30;

      float g_r = 255 / (1 + exp(-1 * r * (preR-127.5f)));
      float g_g = 255 / (1 + exp(-1 * r * (preG-127.5f)));
      float g_b = 255 / (1 + exp(-1 * r * (preB-127.5f)));

      int R = (int) (k_r * (preR + b_r * (g_r - preR)));
      int G = (int) (k_g * (preG + b_g * (g_g - preG)));
      int B = (int) (k_b * (preB + b_b * (g_b - preB)));

      check(R, G, B);
      set( x+i, y+j, color(R, G, B));
    }
  }
}

public void draw_counter() {
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

public void draw_switch() {
  fill(255);
  rect(width/2 - 50, 500, 100, 50);
}

public void draw_selecter(boolean[] s, int t, int u, int v) {
  if (s[t]) {
    noFill();
    strokeWeight(5);
    stroke(50, 50, 255);
    rect((w + distance_width) * (v+1) -3, (h + distance_height) * (u+1) -3, w+3, h+3);
  }
}

public void draw_sketches() {
  for (int i=0; i<2; i++) {
    for (int j=0; j<5; j++) {
      int k = i*5 + j;
      draw_selecter(selected, k, i, j);
      sketch((w + distance_width) * (j+1), (h + distance_height) * (i+1), w, h, parameters[k][0], parameters[k][1], parameters[k][2], parameters[k][3], parameters[k][4], parameters[k][5]);
    }
  }
}

public void image_setup() {
  img = loadImage("sample1.jpg");
  img.resize(img.width/8, img.height/8);
  w = img.width;
  h = img.height;
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "opencanpas_1_0_1" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
