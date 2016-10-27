package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/2016.
 * #91 https://leetcode.com/problems/decode-ways/
 */
public class DecodeWays {
  public int numDecodings(String s) {
    if(s == null || s.isEmpty()){
      return 0;
    }
    char[] arr = s.toCharArray();
    int len = arr.length;
    int[] result = new int[len+1];
    result[0] = 1;

    if(arr[0] <= '0' || arr[0] > '9'){
      return 0;
    }else{
      result[1] = 1;
    }
    for(int i = 2; i <= len; i++){
      char curr = arr[i-1];
      char last = arr[i-2];
      if(curr < '0' || curr > '9'){
        return 0;
      }else if(curr == '0' && (last < '1' || last > '2')){
        return 0;
      }
      if(curr != '0'){
        result[i] += result[i-1];
      }
      if((last == '1' && curr >= '0' && curr <= '9')
              || (last == '2' && curr >= '0' && curr <= '6')){
        result[i] += result[i-2];
      }
    }

    return result[len];
  }
}
