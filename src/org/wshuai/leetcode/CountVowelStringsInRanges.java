package org.wshuai.leetcode;

/**
 * Created by Wei on 06/29/2025.
 * #2559 https://leetcode.com/problems/count-vowel-strings-in-ranges/
 */
public class CountVowelStringsInRanges {

    // time O(m + n), space O(n)
    public int[] vowelStrings(String[] words, int[][] queries) {
        int n = words.length, m = queries.length;
        int[] res = new int[m];
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) { // O(n)
            prefix[i + 1] = prefix[i] + isVowel(words[i]);
        }
        for (int i = 0; i < m; i++) { // O(m)
            int start = queries[i][0], end = queries[i][1];
            res[i] = prefix[end + 1] - prefix[start];
        }
        return res;
    }

    private int isVowel(String word) {
        char c1 = word.charAt(0), c2 = word.charAt(word.length() - 1);
        return (c1 == 'a' || c1 == 'e' || c1 == 'i' || c1 == 'o' || c1 == 'u')
                && (c2 == 'a' || c2 == 'e' || c2 == 'i' || c2 == 'o' || c2 == 'u') ? 1 : 0;
    }
}
