package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 06/23/2025.
 * #3371 https://leetcode.com/problems/identify-the-largest-outlier-in-an-array/
 */
public class IdentifyTheLargestOutlierInAnArray {

    // time O(n), space O(n)
    public int getLargestOutlierEnumerateSum(int[] nums) {
        int res = Integer.MIN_VALUE, n = nums.length, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            sum += num;
            map.merge(num, 1, Integer::sum);
        }
        for (int i = 0; i < n; i++) {
            int x = sum - 2 * nums[i];
            if (map.containsKey(x) && (x != nums[i] || map.get(x) > 1)) {
                res = Math.max(res, x);
            }
        }
        return res;
    }

    // time O(n), space O(n)
    public int getLargestOutlierEnumerateOutlier(int[] nums) {
        int res = Integer.MIN_VALUE, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            sum += num;
            map.merge(num, 1, Integer::sum);
        }
        for (int num : nums) {
            if ((sum - num) % 2 == 0) {
                int x = (sum - num) / 2;
                if (map.containsKey(x) && (x != num || map.get(x) > 1)) {
                    res = Math.max(res, num);
                }
            }
        }
        return res;
    }

    // time O(n), space O(MAX)
    public int getLargestOutlier(int[] nums) {
        int res = Integer.MIN_VALUE, sum = 0;
        int[] map = new int[2_001];
        for (int num : nums) {
            sum += num;
            map[num + 1_000]++;
        }
        for (int num : nums) {
            map[num + 1_000]--;
            if ((sum - num) % 2 == 0) {
                int val = (sum - num) / 2;
                if (val >= -1_000 && val <= 1_000 && map[val + 1_000] > 0) {
                    res = Math.max(res, num);
                }
            }
            map[num + 1_000]++;
        }
        return res;
    }
}
