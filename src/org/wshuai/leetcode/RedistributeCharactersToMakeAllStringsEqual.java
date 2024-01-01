package org.wshuai.leetcode;

/**
 * Created by Wei on 01/01/2024.
 * #1897 https://leetcode.com/problems/redistribute-characters-to-make-all-strings-equal/
 */
public class RedistributeCharactersToMakeAllStringsEqual {

    // time O(n), space O(1)
    public boolean makeEqual(String[] words) {
        int[] count = new int[26];
        for (String s : words) {
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (count[i] % words.length != 0) {
                return false;
            }
        }
        return true;
    }
}
