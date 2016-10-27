package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/2016.
 * #16 https://leetcode.com/problems/3sum-closest/
 */
public class ThreeSumClosest {
  public int threeSumClosest(int[] nums, int target) {
    int cls = 0;
    int diff = Integer.MAX_VALUE;

    int len = nums.length;
    int len1 = len - 1;
    int len2 = len - 2;
    for (int i = 0; i < len2; i++)
    {
      for (int j = i + 1; j < len1; j++)
      {
        for (int k = j + 1; k < len; k++)
        {
          int sum = nums[i] + nums[j] + nums[k];
          int cDiff = Math.abs(sum - target);
          if (cDiff < diff)
          {
            diff = cDiff;
            cls = sum;
            if (diff == 0)
            {
              return cls;
            }
          }
        }
      }
    }

    return cls;
  }
}
