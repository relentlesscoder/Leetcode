package org.wshuai.leetcode;

/**
 * Created by Wei on 08/05/2025.
 * #2516 https://leetcode.com/problems/take-k-of-each-character-from-left-and-right/
 */
public class TakeKOfEachCharacterFromLeftAndRight {

    // time O(n), space O(1)
    public int takeCharacters(String s, int k) {
        // This problem can be converted to find the length ml of the
        // longest subarray with count(a) <= total(a) - k,
        // count(b) <= total(b) - k and count(c) <= total(c) - k, then
        // the result is n - ml.
        int longest = 0, n = s.length();
        int[] freq = new int[]{-k, -k, -k};
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        if (freq[0] < 0 || freq[1] < 0 || freq[2] < 0) {
            return -1;
        }
        for (int i = 0, j = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']--;
            while (freq[s.charAt(i) - 'a'] < 0) {
                freq[s.charAt(j++) - 'a']++;
            }
            longest = Math.max(longest, i - j + 1);
        }
        return n - longest;
    }
}
