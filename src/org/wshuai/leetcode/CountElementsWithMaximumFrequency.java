package org.wshuai.leetcode;

/**
 * Created by Wei on 02/03/2024.
 * #3005 https://leetcode.com/problems/count-elements-with-maximum-frequency/
 */
public class CountElementsWithMaximumFrequency {

    // time O(n), space O(n)
    public int maxFrequencyElements(int[] nums) {
        int res = 0, maxFreq = 0;
        int[] freq = new int[101];
        for (int num : nums) {
            maxFreq = Math.max(maxFreq, ++freq[num]);
        }
        for (int i = 1; i <= 100; i++) {
            if (freq[i] == maxFreq) {
                res += maxFreq;
            }
        }
        return res;
    }
}
