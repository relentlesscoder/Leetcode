package org.wshuai.leetcode;

/**
 * Created by Wei on 09/20/2025.
 * #2982 https://leetcode.com/problems/find-longest-special-substring-that-occurs-thrice-ii/
 */
public class FindLongestSpecialSubstringThatOccursThriceII {

    // time O(n), space O(n)
    public int maximumLength(String s) {
        int res = -1, length = 0;
        int[][] substringLengths = new int[26][3];
        char prev = '#';
        for (int i = 0; i < 26; i++) {
            substringLengths[i] = new int[]{-1, -1, -1};
        }
        // substringLengths[c] stores the three longest length of special strings ends with c
        for (char curr : s.toCharArray()) {
            if (curr == prev) {
                length++;
            } else {
                prev = curr;
                length = 1;
            }
            int index = curr - 'a', minLength = min(substringLengths[index]);
            if (length > minLength) {
                if (substringLengths[index][0] == minLength) {
                    substringLengths[index][0] = length;
                } else if (substringLengths[index][1] == minLength) {
                    substringLengths[index][1] = length;
                } else {
                    substringLengths[index][2] = length;
                }
            }
        }
        for (int i = 0; i < 26; i++) {
            res = Math.max(res, min(substringLengths[i]));
        }
        return res;
    }

    private int min(int[] arr) {
        return Math.min(arr[0], Math.min(arr[1], arr[2]));
    }

    // time O(n), space O(n)
    public int maximumLengthCumulativeSum(String s) {
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

/**
 * Key observation:
 * Example 1: a
 * There is exactly one special substring: a.

 * Example 2: aa
 * There are three special substrings: a, a, aa. Here, a appears twice and aa appears once.

 * Example 3: aaa
 * There are six special substrings: a, a, a, aa, aa, aaa. Here, a appears thrice, aa appears twice,
 * and aaa appears once.
 */
