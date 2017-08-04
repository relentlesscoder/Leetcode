package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Wei on 8/3/17.
 * #327 https://leetcode.com/problems/count-of-range-sum/
 */
public class CountOfRangeSum {
  public int countRangeSum(int[] nums, int lower, int upper) {
    List<Long> cand = new ArrayList<Long>();
    cand.add(Long.MIN_VALUE);
    cand.add(0L);
    long[] sum = new long[nums.length + 1];
    for(int i = 1; i < sum.length; i++){
      sum[i] = sum[i-1] + nums[i-1];
      cand.add(sum[i]);
      cand.add(lower + sum[i-1] - 1);
      cand.add(upper + sum[i-1]);
    }
    Collections.sort(cand);

    int[] bit = new int[cand.size()];
    for(int i = 0; i < sum.length; i++){
      add(bit, Collections.binarySearch(cand, sum[i]), 1);
    }
    int ans = 0;
    for(int i = 1; i < sum.length; i++){
      add(bit, Collections.binarySearch(cand, sum[i-1]), -1);
      ans += find(bit, Collections.binarySearch(cand, upper + sum[i-1]));
      ans -= find(bit, Collections.binarySearch(cand, lower + sum[i-1] - 1));
    }
    return ans;
  }

  private void add(int[] bit, int idx, int delta){
    for(int i = idx; i < bit.length; i += i&(-i)){
      bit[i] += delta;
    }
  }

  private int find(int[] bit, int idx){
    int sum = 0;
    for(int i = idx; i > 0; i -= i&(-i)){
      sum += bit[i];
    }
    return sum;
  }
}
