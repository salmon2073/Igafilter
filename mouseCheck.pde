void mouseCheck(int[] temp) {
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

void setTemp(int[] temp, int k) {
  if (selected[k]) {
    selected[k] = false;
    delete_selected(temp, k);
  }
  else {
    selected[k] = true;
    set_selected(temp, k);
  }
}

void initTemp() {
  for (int i=0; i<temp.length; i++) {
    temp[i] =0;
  }
}

void delete_selected(int[] temp, int k) {
  for (int i=0; i<temp.length; i++) {
    if (temp[i] == k) {
      temp[i] = 0;
    }
  }
}


void set_selected(int[] temp, int k) {
  for (int i=0; i<temp.length; i++) {
    if (temp[i] == 0) {
      temp[i] =k;
      break;
    }
  }
}

