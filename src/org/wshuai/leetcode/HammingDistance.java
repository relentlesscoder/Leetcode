package org.wshuai.leetcode;

/**
 * Created by Wei on 1/3/2017.
 * #461 https://leetcode.com/problems/hamming-distance/
 */
public class HammingDistance {
  public int hammingDistance(int x, int y) {
    int z = x^y;
    int mask = 1;
    int count = 0;
    for(int i = 0; i < 32; i++){
      count += mask&(z >> i);
    }
    return count;
  }
}
