package org.wshuai.leetcode;

/**
 * Created by Wei on 08/05/2025.
 * #2516 https://leetcode.com/problems/take-k-of-each-character-from-left-and-right/
 */
public class TakeKOfEachCharacterFromLeftAndRight {

    // time O(n), space O(1)
    public int takeCharacters(String s, int k) {
        int n = s.length(), maxWindowSize = 0;
        int[] count = new int[3], window = new int[3];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        if (count[0] < k || count[1] < k || count[2] < k) {
            return -1;
        }
        for (int i = 0, j = 0; j < n; j++) {
            window[s.charAt(j) - 'a']++;
            while (i <= j &&
                    (count[0] - window[0] < k
                            || count[1] - window[1] < k
                            || count[2] - window[2] < k)) {
                window[s.charAt(i++) - 'a']--;
            }
            maxWindowSize = Math.max(maxWindowSize, j - i + 1);
        }
        return n - maxWindowSize;
    }
}
