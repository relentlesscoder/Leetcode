package org.wshuai.leetcode;

/**
 * Created by Wei on 06/29/2025.
 * #3442 https://leetcode.com/problems/maximum-difference-between-even-and-odd-frequency-i/
 */
public class MaximumDifferenceBetweenEvenAndOddFrequencyI {

    // time O(n), space O(1)
    public int maxDifference(String s) {
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }
        int maxOddFreq = 0, minEvenFreq = 100;
        for (int f : freq) {
            if (f == 0) {
                continue;
            }
            if (f % 2 == 0) {
                minEvenFreq = Math.min(minEvenFreq, f);
            } else {
                maxOddFreq = Math.max(maxOddFreq, f);
            }
        }
        return maxOddFreq - minEvenFreq;
    }
}
