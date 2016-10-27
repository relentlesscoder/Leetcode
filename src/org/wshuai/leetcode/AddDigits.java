package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #258 https://leetcode.com/problems/add-digits/
 */
public class AddDigits {
  //Explanation: http://www.cnblogs.com/grandyang/p/4741028.html
  public int addDigits(int num) {
    return 1 + (num - 1)%9;
  }
}
