package org.wshuai.leetcode;

/**
 * Created by Wei on 08/25/2025.
 * #3541 https://leetcode.com/problems/find-most-frequent-vowel-and-consonant/
 */
public class FindMostFrequentVowelAndConsonant {

    // time O(n), space O(1)
    public int maxFreqSum(String s) {
        int maxVowel = 0, maxConsonant = 0;
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (i == 0 || i == 4 || i == 8 || i == 14 || i == 20) {
                maxVowel = Math.max(maxVowel, freq[i]);
            } else {
                maxConsonant = Math.max(maxConsonant, freq[i]);
            }
        }
        return maxVowel + maxConsonant;
    }
}
