package org.wshuai.leetcode;

/**
 * Created by Wei on 8/12/2016.
 */
public class ZigZagConversion {
  public static String convert(String s, int numRows) {
    if(numRows <= 0){
      throw new IllegalArgumentException("Invalid input.");
    }

    if(s == null || s.isEmpty()){
      return "";
    }
    if(numRows == 1){
      return s;
    }
    int len = s.length();
    if(len <= numRows){
      return s;
    }
    StringBuilder sb = new StringBuilder();
    int size = 2*numRows - 2;
    for(int i = 0; i < numRows; i++){
      for(int j = i; j < len; j+=size){
        sb.append(s.charAt(j));
        if(i != 0 && i != (numRows - 1)){
          int index = j+size-2*i;
          if(index < len){
            sb.append(s.charAt(index));
          }
        }
      }
    }
    return sb.toString();
  }
}
