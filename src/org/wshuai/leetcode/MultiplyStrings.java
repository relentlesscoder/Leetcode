package org.wshuai.leetcode;

/**
 * Created by Wei on 10/5/16.
 */
public class MultiplyStrings {
  public String multiply(String num1, String num2) {
    if(num1 == null || num1.isEmpty() || num2 == null || num2.isEmpty()){
      throw new IllegalArgumentException("Invalid input.");
    }

    char[] arr1 = new StringBuilder(num1).reverse().toString().toCharArray();
    char[] arr2 = new StringBuilder(num2).reverse().toString().toCharArray();
    int len1 = arr1.length;
    int len2 = arr2.length;
    int len = len1+len2;
    int[] arr = new int[len];
    for(int i = 0; i < len1; i++){
      for(int j = 0; j < len2; j++){
        arr[i+j] += (arr1[i] - 48)*(arr2[j]-48);
      }
    }

    int carry = 0;
    for(int i = 0; i < len; i++){
      int val = arr[i] + carry;
      arr[i] = val%10;
      carry = val/10;
    }

    StringBuilder sb = new StringBuilder();
    int idx = len - 1;
    while(idx >= 0 && arr[idx] == 0){
      idx--;
    }
    //Handle result like "0000"
    if(idx == -1){
      return "0";
    }
    for(int i = idx; i >= 0; i--){
      sb.append(Integer.toString(arr[i]));
    }
    return sb.toString();
  }
}
