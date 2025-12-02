package org.wshuai.leetcode;

/**
 * Created by Wei on 10/03/2023.
 * #2799 https://leetcode.com/problems/count-complete-subarrays-in-an-array/
 */
public class CountCompleteSubarraysInAnArray {

    // time O(n), space O(MAX)
    public int countCompleteSubarrays(int[] nums) {
        int res = 0, n = nums.length, distinct = 0, max = 0;
        int[] map = new int[2_001];
        for (int num : nums) {
            if (map[num]++ == 0) {
                distinct++;
            }
            max = Math.max(max, num);
        }
        int[] freq = new int[max + 1];
        for (int i = 0, j = 0, count = 0; i < n; i++) {
            if (freq[nums[i]]++ == 0) {
                count++;
            }
            while (count == distinct) {
                if (--freq[nums[j++]] == 0) {
                    count--;
                }
            }
            res += j;
        }
        return res;
    }
}
