package org.wshuai.leetcode;

/**
 * Created by Wei on 9/19/2016.
 */
public class FindTheDifference {
  public char findTheDifference(String s, String t) {
    if(s == null || t == null){
      throw new IllegalArgumentException("Invalid input.");
    }
    if(t.isEmpty()){
      throw new IllegalArgumentException("Invalid input.");
    }

    int sum = 0;
    char[] arr1 = s.toCharArray();
    char[] arr2 = t.toCharArray();
    int len1 = arr1.length;
    int len2 = arr2.length;
    for(int i = 0; i < len2; i++){
      sum += (int)arr2[i];
    }
    for(int i = 0; i < len1; i++){
      sum -= (int)arr1[i];
    }
    return (char)sum;
  }
}
