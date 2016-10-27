package org.wshuai.leetcode;

/**
 * Created by Wei on 8/13/2016.
 * #9 https://leetcode.com/problems/palindrome-number/
 */
public class PalindromeNumber {

  public static boolean isPalindrome(int x) {
    if(x < 0){
      return false;
    }
    if(x >= 0 && x <= 9){
      return true;
    }
    String s = Integer.toString(x);
    int len = s.length();
    int n = 1;
    for(int i = 0; i < len - 1; i++){
      n*=10;
    }
    int left = len - 1;
    int right = 0;
    while(left > right){
      int ln = x/n;
      x = x%n;
      int rn = x%10;
      x = x/10;
      if(ln != rn){
        return false;
      }

      n /= 100;
      left--;
      right++;
    }

    return true;
  }

  public static boolean isPalindromeUsingStr(int x) {
    if(x < 0){
      return false;
    }
    if(x >= 0 && x <= 9){
      return true;
    }
    String s = Integer.toString(x);
    int len = s.length();
    int left = 0;
    int right = len - 1;
    while(left < right){
      if(s.charAt(left) != s.charAt(right)){
        return false;
      }

      left++;
      right--;
    }

    return true;
  }
}
