package org.wshuai.leetcode;

/**
 * Created by Wei on 03/02/2021.
 * #1639 https://leetcode.com/problems/number-of-ways-to-form-a-target-string-given-a-dictionary/
 */
public class NumberOfWaysToFormATargetStringGivenADictionary {

    private static final long MOD = 1_000_000_007;

    // time O(m*n), space O(n)
    public int numWays(String[] words, String target) {
        int n = target.length(), m = words[0].length();
        long[] res = new long[n + 1];
        res[0] = 1;
        for(int i = 0; i < m; i++){
            int[] count = new int[26];
            for(String w : words){
                count[w.charAt(i) - 'a']++;
            }
            for(int j = n - 1; j >= 0; j--){
                res[j + 1] += res[j] * count[target.charAt(j) - 'a'] % MOD;
            }
        }
        return (int)(res[n] % MOD);
    }
}
