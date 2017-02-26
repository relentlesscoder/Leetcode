package org.wshuai.leetcode;

/**
 * Created by Wei on 2/25/17.
 * #520 https://leetcode.com/problems/detect-capital/
 */
public class DetectCapital {
  public boolean detectCapitalUse(String word) {
    char[] arr = word.toCharArray();
    int len = arr.length;
    char first = arr[0];
    boolean cap = first >= 'A' && first <= 'Z';
    int c1 = 0;
    int c2 = 0;
    for(int i = 1; i < len; i++){
      if(arr[i] >= 'A' && arr[i] <= 'Z'){
        c1++;
      }else{
        c2++;
      }
    }
    return cap ? (c1==0 || c2==0) : c1==0;
  }
}
