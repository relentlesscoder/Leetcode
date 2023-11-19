package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 08/31/2023.
 * #1838 https://leetcode.com/problems/frequency-of-the-most-frequent-element/
 */
public class FrequencyOfTheMostFrequentElement {

    // time O(n*log(n)), space O(1)
    public int maxFrequency(int[] nums, int k) {
        int res = 0, i = 0;
        long sum = 0;
        Arrays.sort(nums);
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            while (sum + k < (long)nums[j] * (j - i + 1)) {
                sum -= nums[i];
                i++;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}
