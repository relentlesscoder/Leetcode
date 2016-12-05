package org.wshuai.leetcode;

/**
 * Created by Wei on 11/20/16.
 * #67 https://leetcode.com/problems/add-binary/
 */
public class AddBinary {
  public String addBinary(String a, String b) {
    if(a == null || a.isEmpty()){
      return b;
    }
    if(b == null || b.isEmpty()){
      return a;
    }
    char[] a1 = a.toCharArray();
    char[] a2 = b.toCharArray();
    int len1 = a1.length;
    int len2 = a2.length;
    StringBuilder sb = new StringBuilder();
    int carry = 0;
    int sum = 0;
    while(len1 > 0 || len2 > 0){
      if(len1 == 0){
        sum = Character.getNumericValue(a2[len2 - 1]) + carry;
        len2--;
      }
      else if(len2 == 0){
        sum = Character.getNumericValue(a1[len1 - 1]) + carry;
        len1--;
      }
      else{
        sum = Character.getNumericValue(a1[len1 - 1]) + Character.getNumericValue(a2[len2 - 1]) + carry;
        len1--;
        len2--;
      }
      carry = sum/2;
      sum = sum%2;
      sb.append(Integer.toString(sum));
    }
    if(carry > 0){
      sb.append(Integer.toString(carry));
    }

    return sb.reverse().toString();
  }
}
