package org.wshuai.leetcode;

/**
 * Created by Wei on 9/20/2016.
 */
public class ReadNCharactersGivenRead4 {
  /**
   * @param buf Destination buffer
   * @param n   Maximum number of characters to read
   * @return    The number of characters read
   */
  public int read(char[] buf, int n) {
    if(n <= 0){
      return 0;
    }

    int count = -1;
    while(n >= 0){
      char[] temp = new char[4];
      int c = read4(temp);
      if(c == 0){
        return count + 1;
      }
      int x = c > n ? n : c;
      for(int j = 0; j < x; j++){
        count++;
        buf[count] = temp[j];
      }
      if(c > n || c < 4){
        return count + 1;
      }
      n -= 4;
    }

    return count + 1;
  }

  // Fake method
  public int read4(char[] buf){
    return 0;
  }
}
