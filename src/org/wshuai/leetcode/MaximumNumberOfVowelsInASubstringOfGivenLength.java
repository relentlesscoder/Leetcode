package org.wshuai.leetcode;

/**
 * Created by Wei on 05/24/2020.
 * #1456 https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/
 */
public class MaximumNumberOfVowelsInASubstringOfGivenLength {

    // time O(n), space O(1)
    public int maxVowels(String s, int k) {
        int res = 0, n = s.length(), count = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                count++;
            }

            if (i - k + 1 < 0) {
                continue;
            }
            res = Math.max(res, count);
            if (res == k) {
                break;
            }

            c = s.charAt(i - k + 1);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                count--;
            }
        }
        return res;
    }
}
