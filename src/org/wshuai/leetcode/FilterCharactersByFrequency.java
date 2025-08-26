package org.wshuai.leetcode;

/**
 * Created by Wei on 08/25/2025.
 * #3662 https://leetcode.com/problems/filter-characters-by-frequency/
 */
public class FilterCharactersByFrequency {

    // time O(n), space O(n)
    public String filterCharacters(String s, int k) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (freq[c - 'a'] >= k) {
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
