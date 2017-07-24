void check_switch(float p[][], boolean b[], int x, int y, int w, int h) {
  if (mouseX >= x && mouseX <= x+w && mouseY >= y && mouseY <= y+h ) {
    setparam(p, temp[0], temp[1], temp[2]);
    initTemp();
    for (int i=0; i < 10; i++) {
      b[i] = false;
    }
  }
}

