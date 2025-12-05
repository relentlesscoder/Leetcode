package org.wshuai.leetcode;

/**
 * Created by Wei on 06/29/2025.
 * #2559 https://leetcode.com/problems/count-vowel-strings-in-ranges/
 */
public class CountVowelStringsInRanges {

    // time O(m + n), space O(n)
    public int[] vowelStrings(String[] words, int[][] queries) {
        int n = words.length, m = queries.length;
        int[] res = new int[m], prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            boolean isVowelString =
                    isVowel(words[i].charAt(0)) && isVowel(words[i].charAt(words[i].length() - 1));
            prefixSum[i + 1] = prefixSum[i] + (isVowelString ? 1 : 0);
        }
        for (int i = 0; i < m; i++) {
            res[i] = prefixSum[queries[i][1] + 1] - prefixSum[queries[i][0]];
        }
        return res;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
