package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #319 https://leetcode.com/problems/bulb-switcher/
 */
public class BulbSwitcher {

  //Optimal solution
  public int bulbSwitch(int n) {
    return (int)Math.sqrt(n);
  }

  //Count all square numbers less than n
  public int bulbSwitchSqures(int n) {
    int x = 1;
    while(x*x <= n){
      x++;
    }
    return x-1;
  }

  //TLE
  public int bulbSwitchNaive(int n) {
    boolean[] bulbs = new boolean[n];
    for(int i=0; i < n; i++){
      for(int j=i; j < n; j+=(i+1)){
        bulbs[j] = !bulbs[j];
      }
    }

    int count = 0;
    for(int i=0; i < n; i++){
      if(bulbs[i]){
        count++;
      }
    }

    return count;
  }
}
