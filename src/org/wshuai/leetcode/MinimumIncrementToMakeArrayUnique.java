package org.wshuai.leetcode;

/**
 * Created by Wei on 9/28/2019.
 * #945 https://leetcode.com/problems/minimum-increment-to-make-array-unique/
 */
public class MinimumIncrementToMakeArrayUnique {
  public int minIncrementForUnique(int[] A) {
    int[] count = new int[100000];
    for (int x: A){
      count[x]++;
    }

    int ans = 0, taken = 0;

    // use variable taken to make sure only larger number will be considered
    for (int x = 0; x < 100000; ++x) {
      if (count[x] >= 2) {
        taken += count[x] - 1;
        ans -= x * (count[x] - 1);
      }
      else if (taken > 0 && count[x] == 0) {
        taken--;
        ans += x;
      }
    }

    return ans;
  }
}
