package org.wshuai.leetcode;

/**
 * Created by Wei on 12/02/2025.
 * #3306 https://leetcode.com/problems/count-of-substrings-containing-every-vowel-and-k-consonants-ii/
 */
public class CountOfSubstringsContainingEveryVowelAndKConsonantsII {

    private static final int A = 'a' - 'a';
    private static final int E = 'e' - 'a';
    private static final int I = 'i' - 'a';
    private static final int O = 'o' - 'a';
    private static final int U = 'u' - 'a';

    // time O(n), space O(1)
    public long countOfSubstrings(String word, int k) {
        char[] arr = word.toCharArray();
        return solve(arr, k) - solve(arr, k + 1);
    }

    private long solve(char[] word, int k) {
        long res = 0;
        int n = word.length;
        int[] freq = new int[26];
        for (int i = 0, j = 0, consonants = 0; i < n; i++) {
            boolean isVowel = word[i] == 'a' || word[i] == 'e'
                    || word[i] == 'i' || word[i] == 'o' || word[i] == 'u';
            if (!isVowel) {
                consonants++;
            }
            freq[word[i] - 'a']++;
            while (freq[A] > 0 && freq[E] > 0 && freq[I] > 0
                    && freq[O] > 0 && freq[U] > 0 && consonants >= k) {
                isVowel = word[j] == 'a' || word[j] == 'e'
                        || word[j] == 'i' || word[j] == 'o' || word[j] == 'u';
                if (!isVowel) {
                    consonants--;
                }
                freq[word[j++] - 'a']--;
            }
            res += j;
        }
        return res;
    }
}
