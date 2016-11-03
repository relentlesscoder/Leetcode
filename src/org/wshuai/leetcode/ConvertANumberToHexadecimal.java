package org.wshuai.leetcode;

/**
 * Created by Wei on 11/2/2016.
 * #405 https://leetcode.com/problems/convert-a-number-to-hexadecimal/
 */
public class ConvertANumberToHexadecimal {
  public String toHex(int num) {
    if(num == 0){
      return "0";
    }
    StringBuilder sb = new StringBuilder();
    char[] map = new char[]{'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
    while(num != 0){
      //0x0000000F
      sb.append(map[num&15]);
      num = (num >>> 4);
    }
    return sb.reverse().toString();
  }
}
