package org.wshuai.leetcode;

/**
 * Created by Wei on 09/20/2025.
 * #2981 https://leetcode.com/problems/find-longest-special-substring-that-occurs-thrice-i/
 */
public class FindLongestSpecialSubstringThatOccursThriceI {

    // time O(n), space O(n)
    public int maximumLength(String s) {
        int res = -1, n = s.length(), length = 1;
        int[][] freq = new int[26][n + 1];
        char prev = s.charAt(0);
        // freq[c][l] stores the count of special substring ends with character c with length l
        freq[s.charAt(0) - 'a'][1] = 1;
        for (int i = 1; i < n; i++) {
            char curr = s.charAt(i);
            if (curr == prev) {
                length++;
                int count = 1;
                freq[curr - 'a'][length] += 1;
            } else {
                prev = curr;
                length = 1;
                freq[curr - 'a'][1] += 1;
            }
        }
        // for each character c, calculate the cumulative sum for each length
        for (int i = 0; i < 26; i++) {
            for (int len = n - 1; len >= 1; len--) {
                freq[i][len] += freq[i][len + 1];
                if (freq[i][len] >= 3) {
                    res = Math.max(res, len);
                    break;
                }
            }
        }
        return res;
    }
}
