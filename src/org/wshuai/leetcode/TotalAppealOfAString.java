package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/23/2023.
 * #2262 https://leetcode.com/problems/total-appeal-of-a-string/
 */
public class TotalAppealOfAString {

    // time O(n), space O(1)
    public long appealSum(String s) {
        long res = 0, curr = 0;
        int[] prev = new int[26];
        Arrays.fill(prev, -1);
        for (int i = 0; i < s.length(); i++) {
            curr += i - prev[s.charAt(i) - 'a'];
            prev[s.charAt(i) - 'a'] = i;
            res += curr;
        }
        return res;
    }
    /*
    The recurrence relation here is: dp(i) = dp(i - 1) + (i - prev index of the same element as s[i]).
    dp(i) means the total appeal of substrings ending at and including the i-th position and the final answer should be sum(dp(i)) for 0 <= i < n.
    Let's say we are given "a b b c a" as an input and we are currently looking at the last 'a'(index 4) in the recurrence relation.
    Then, there will be 5 substrings as follows we should care about to calculate dp(4) by the definition above; dp(4) = dp(3) + (4 - 0).
    - - - - a (substr 1)
    - - - c a (substr 2)
    - - b c a (substr 3)
    - b b c a (substr 4)
    a b b c a (substr 5)
    First, by taking dp(3) we count the total appeal of all the substrings without counting the current 'a'(index 4) since
    if we unconditionally counted the current 'a', we would end up over-counting it and that is what we should care about;
    The substr 5 is an example. Note that the substr 2, 3, 4 and 5 are covered here, but the current 'a' is not counted yet in those
    substrings since it might be a duplicate, not an addition. This is safe since we don't over-count duplicates.
    Second, by adding (4 - 0) we now count the number of the current 'a's contributions to the final answer.
    This means that we count the current 'a' only in the substr 1, 2, 3 and 4 since only in those substrings the
    current 'a' is an addition, not a duplicate.
     */

    public long appealSumCombinatorics(String s) {
        // https://leetcode.com/problems/total-appeal-of-a-string/solutions/1999226/Combinatorics/
        long res = 0;
        int n = s.length();
        int[] prev = new int[26];
        Arrays.fill(prev, -1);
        for (int i = 0; i < n; i++) {
            res += (i - prev[s.charAt(i) - 'a']) * (n - i);
            prev[s.charAt(i) - 'a'] = i;
        }
        return res;
    }
}
