package org.wshuai.leetcode;

/**
 * Created by Wei on 10/23/16.
 * #415 https://leetcode.com/problems/add-strings/
 */
public class AddStrings {
  public String addStrings(String num1, String num2) {
    if(num1 == null || num1.length() == 0){
      return num2;
    }
    if(num2 == null || num2.length() == 0){
      return num1;
    }
    StringBuilder sb = new StringBuilder();
    int carry = 0;
    char[] arr1 = num1.toCharArray();
    char[] arr2 = num2.toCharArray();
    int len1 = arr1.length;
    int len2 = arr2.length;
    int i = len1-1;
    int j = len2-1;
    while(i >= 0 || j >= 0 || carry > 0){
      int x = 0;
      int y = 0;
      if(j >= 0){
        x = Character.getNumericValue(arr2[j]);
      }
      if(i >= 0){
        y = Character.getNumericValue(arr1[i]);
      }
      int sum = x + y + carry;
      sb.insert(0, Integer.toString(sum%10));
      carry = sum/10;
      i--;
      j--;
    }
    return sb.toString();
  }
}
