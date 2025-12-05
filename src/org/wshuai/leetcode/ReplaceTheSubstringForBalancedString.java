package org.wshuai.leetcode;

/**
 * Created by Wei on 11/07/2019.
 * #1234 https://leetcode.com/problems/replace-the-substring-for-balanced-string/
 */
public class ReplaceTheSubstringForBalancedString {

    private static final int A = 'Q' - 'A';
    private static final int B = 'W' - 'A';
    private static final int C = 'E' - 'A';
    private static final int D = 'R' - 'A';

    // time O(n), space O(1)
    public int balancedStringReverse(String s) {
        int n = s.length(), res = n + 1, k = n / 4;
        int[] count = new int[26];
        char[] arr = s.toCharArray();
        for (char x : arr) {
            count[x - 'A']++;
        }
        for (int i = 0, j = 0; i < n; i++) {
            count[s.charAt(i) - 'A']--;
            // Count represents character count outside the current window,
            // if all counts <= k we can always use characters inside the
            // window to replace (this equals to below solution that try to
            // find the shortest substring containing all extra characters).
            while (j < n
                    && count[A] <= k
                    && count[B] <= k
                    && count[C] <= k
                    && count[D] <= k) {
                res = Math.min(res, i - j + 1);
                ++count[s.charAt(j++) - 'A'];
            }
        }
        return res > n ? 0 : res;
    }

    // time O(n), space O(1)
    public int balancedString(String s) {
        int n = s.length(), res = n + 1, k = n / 4;
        int[] target = new int[26], count = new int[26];
        char[] arr = s.toCharArray();
        for (char c : arr) {
            target[c - 'A']++;
        }
        // Calculate number of extra for characters with total
        // count greater than n/4
        for (int i = 0; i < 26; i++) {
            target[i] = Math.max(0, target[i] - k);
        }
        for (int i = 0, j = 0; i < n; i++) {
            count[s.charAt(i) - 'A']++;
            // The shortest substring containing all extra characters
            // can be replaced to make the input valid
            // e.g. "WQERQQQWERWQ", substring "QQ" is the shortest that
            // can be replaced
            while (j < n
                    && count[A] >= target[A]
                    && count[B] >= target[B]
                    && count[C] >= target[C]
                    && count[D] >= target[D]) {
                res = Math.min(res, i - j + 1);
                --count[s.charAt(j++) - 'A'];
            }
        }
        return res > n ? 0 : res;
    }
}
