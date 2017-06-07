package org.wshuai.leetcode;

/**
 * Created by Wei on 9/20/2016.
 * #157 https://leetcode.com/problems/read-n-characters-given-read4/
 */
public class ReadNCharactersGivenRead4 {
  /**
   * @param buf Destination buffer
   * @param n   Maximum number of characters to read
   * @return    The number of characters read
   */
  public int read(char[] buf, int n) {
    for(int i = 0; i < n; i += 4){
      char[] tmp = new char[4];
      int len = read4(tmp);
      System.arraycopy(tmp, 0, buf, i, Math.min(len, n-i));
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
