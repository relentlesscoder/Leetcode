package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #38 https://leetcode.com/problems/count-and-say/
 */
public class CountAndSay {
  public String countAndSay(int n) {
    if(n <= 0){
      return "";
    }
    String s = "1";
    if(n > 1){
      for(int i = 1; i < n; i++){
        StringBuilder sb = new StringBuilder();
        char[] arr = s.toCharArray();
        char init = arr[0];
        int count = 0;
        for(int j = 0; j < arr.length; j++){
          if(init == arr[j]){
            count++;
          }else{
            sb.append(Integer.toString(count));
            sb.append(init);
            init = arr[j];
            count = 1;
          }
        }
        sb.append(Integer.toString(count));
        sb.append(init);
        s = sb.toString();
      }
    }
    return s;
  }
}
