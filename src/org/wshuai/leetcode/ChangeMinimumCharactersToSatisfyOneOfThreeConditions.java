package org.wshuai.leetcode;

/**
 * Created by Wei on 12/28/2023.
 * #1737 https://leetcode.com/problems/change-minimum-characters-to-satisfy-one-of-three-conditions/
 */
public class ChangeMinimumCharactersToSatisfyOneOfThreeConditions {

    // time O(m + n), space O(1)
    public int minCharacters(String a, String b) {
        int m = a.length(), n = b.length(), res = m + n;
        int[] c1 = new int[26], c2 = new int[26];
        for (int i = 0; i < m; i++) {
            c1[a.charAt(i) - 'a']++;
        }
        for (int i = 0; i < n; i++) {
            c2[b.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            res = Math.min(res, m + n - c1[i] - c2[i]); // condition 3 - try change all characters to (char)('a' + i)
            if (i > 0) { // Prefix sum c1[i] denotes count of characters in a that are less or equals to (char)('a' + i)
                c1[i] += c1[i - 1];
                c2[i] += c2[i - 1];
            }
            if (i < 25) {
                // For each of i (i < 25 since if a contains 'z' then it's impossible to satisfy condition 1), use (char)('a' + i) as
                // the separator thus to satisfy condition 1 we need to change m - c1[i] characters in a and c2[i] characters in b
                // (the final value does not matter). After the change, all characters in a are less than or equals to (char)('a' + i) and
                // all characters in b are greater than (char)('a' + i).
                res = Math.min(res, m - c1[i] + c2[i]); // condition 1
                res = Math.min(res, n - c2[i] + c1[i]); // condition 2
            }
        }
        return res;
    }
}
