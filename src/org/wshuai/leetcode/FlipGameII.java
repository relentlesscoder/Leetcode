package org.wshuai.leetcode;

/**
 * Created by Wei on 11/2/2016.
 * #294 https://leetcode.com/problems/flip-game-ii/
 */
public class FlipGameII {
  public boolean canWin(String s) {
    if(s == null || s.isEmpty()){
      return false;
    }

    return canWinUtil(s.toCharArray(), s.length());
  }

  private boolean canWinUtil(char[] arr, int len){
    for(int i = 0; i < len-1; i++){
      if(arr[i] == '+' && arr[i+1] == '+'){
        arr[i] = '-';
        arr[i+1] = '-';
        boolean win = canWinUtil(arr, len);
        arr[i] = '+';
        arr[i+1] = '+';
        if(!win){
          return true;
        }
      }
    }

    return false;
  }
}
