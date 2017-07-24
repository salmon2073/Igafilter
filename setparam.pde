void setparam(float parameters[][]) {
  for (int i=0; i<parameters.length;i++ ) {
    for (int j=0; j<parameters[0].length;j++) {
      parameters[i][j] = random(1);
    }
  }
}


void setparam(float parameters[][], int p1, int p2, int p3) {

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


void crossing(float a[], float b[], float p1[], float p2[]) {
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


void mutation(int s1, int s2, int s3, float p1[], float p2[], float p3[]) {
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

