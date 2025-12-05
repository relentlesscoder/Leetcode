package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 08/31/2023.
 * #1838 https://leetcode.com/problems/frequency-of-the-most-frequent-element/
 */
public class FrequencyOfTheMostFrequentElement {

    // time O(n * log(n)), space O(1)
    public int maxFrequency(int[] nums, int k) {
        // Sort the array and then for each of the numbers nums[i]
        // find the longest window that we can use k to convert
        // all numbers to nums[i], the maximum length of all the
        // windows is the answer.
        int res = 0, n = nums.length;
        long sum = 0;
        Arrays.sort(nums);
        for (int i = 0, j = 0; i < n; i++) {
            sum += nums[i];
            while (sum + k < (long) nums[i] * (i - j + 1)) {
                sum -= nums[j++];
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}
