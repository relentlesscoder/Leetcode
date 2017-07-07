package org.wshuai.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Wei on 6/7/2017.
 * #158 https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/
 */
public class ReadNCharactersGivenRead4IICallMultipleTimes {
  Queue<Character> remain = new LinkedList<Character>();

  /**
   * @param buf Destination buffer
   * @param n   Maximum number of characters to read
   * @return    The number of characters read
   */
  // See https://segmentfault.com/a/1190000003794420
  public int read(char[] buf, int n) {
    int i = 0;
    while(i < n && !remain.isEmpty()){
      buf[i] = remain.poll();
      i++;
    }
    for(; i < n; i += 4){
      char[] tmp = new char[4];
      int len = read4(tmp);
      if(len > n-i){
        System.arraycopy(tmp, 0, buf, i, n-i);
        for(int j=n-i; j < len; j++){
          remain.offer(tmp[j]);
        }
      }else{
        System.arraycopy(tmp, 0, buf, i, len);
      }

      if(len < 4){
        return Math.min(i+len, n);
      }
    }
    return n;
  }

  // Fake implementation
  public int read4(char[] buf){
    return 0;
  }
}
