package org.wshuai.leetcode;

/**
 * Created by Wei on 8/13/2016.
 * #13 https://leetcode.com/problems/roman-to-integer/
 */
public class RomanToInteger {
  public static int romanToInt(String s) {
    if(s == null){
      throw new IllegalArgumentException("Invalid input.");
    }
    if(s.isEmpty()){
      return 0;
    }
    int result = 0;
    int len = s.length();
    for(int i = 0; i < len; i++){
      char x = s.charAt(i);
      switch (x) {
        case 'I':
          // IV
          if(i != len - 1 && s.charAt(i + 1) == 'V'){
            result += 4;
            i++;
          }
          // IX
          else if(i != len - 1 && s.charAt(i + 1) == 'X'){
            result += 9;
            i++;
          }
          // Single I
          else{
            result += 1;
          }
          break;
        case 'X':
          // XL
          if(i != len - 1 && s.charAt(i + 1) == 'L'){
            result += 40;
            i++;
          }
          // XC
          else if(i != len - 1 && s.charAt(i + 1) == 'C'){
            result += 90;
            i++;
          }
          // Single X
          else{
            result += 10;
          }
          break;
        case 'C':
          // CD
          if(i != len - 1 && s.charAt(i + 1) == 'D'){
            result += 400;
            i++;
          }
          // CM
          else if(i != len - 1 && s.charAt(i + 1) == 'M'){
            result += 900;
            i++;
          }
          // Single C
          else{
            result += 100;
          }
          break;
        case 'V':
          result += 5;
          break;
        case 'L':
          result += 50;
          break;
        case 'D':
          result += 500;
          break;
        case 'M':
          result += 1000;
          break;
        default:
          break;
      }
    }
    return result;
  }
}
