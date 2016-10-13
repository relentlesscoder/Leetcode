package org.wshuai.leetcode;

/**
 * Created by Wei on 10/11/2016.
 * #34 https://leetcode.com/problems/search-for-a-range/
 */
public class SearchForARange {
  public int[] searchRange(int[] nums, int target) {
    int[] res = new int[2];
    res[0] = -1;
    res[1] = -1;

    if (nums == null || nums.length == 0)
    {
      return res;
    }

    int len = nums.length;
    int l = 0;
    int r = len - 1;
    while (l + 1 < r)
    {
      int mid = l + (r - l)/2;
      int mVal = nums[mid];
      if (mVal == target)
      {
        int x = mid;
        int y = mid;
        while (x >= 0 && nums[x] == target)
        {
          x--;
        }
        while (y < len && nums[y] == target)
        {
          y++;
        }
        res[0] = x + 1;
        res[1] = y - 1;
        return res;
      }
      else if (mVal > target)
      {
        r = mid;
      }
      else
      {
        l = mid;
      }
    }
    int rVal = nums[r];
    int lVal = nums[l];
    if (rVal == target && lVal == target)
    {
      res[0] = l;
      res[1] = r;
    }
    else if (rVal == target)
    {
      res[0] = r;
      res[1] = r;
    }
    else if (lVal == target)
    {
      res[0] = l;
      res[1] = l;
    }

    return res;
  }
}
