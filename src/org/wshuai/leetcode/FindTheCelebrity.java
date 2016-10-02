package org.wshuai.leetcode;

/**
 * Created by Wei on 10/1/2016.
 */
public class FindTheCelebrity {
  public int findCelebrity(int n) {
    if(n <= 0){
      return -1;
    }
    int a = 0;
    int b = n - 1;
    while(a < b){
      boolean k = knows(a, b);
      if(k){
        a++;
      }else{
        b--;
      }
    }

    for(int i = 0; i < n; i++){
      if(i != a && (!knows(i, a) || knows(a, i))){
        return -1;
      }
    }

    return a;
  }

  // Fake method for compiling
  private boolean knows(int a, int b){
    return false;
  }
}
