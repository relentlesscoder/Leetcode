package org.wshuai.leetcode;

/**
 * Created by Wei on 09/02/2023.
 * #2287 https://leetcode.com/problems/rearrange-characters-to-make-target-string/description/
 */
public class RearrangeCharactersToMakeTargetString {

    // time O(m + n), space O(1)
    public int rearrangeCharacters(String s, String target) {
        int res = Integer.MAX_VALUE;
        int[] count = new int[26], targetCount = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        for (char c : target.toCharArray()) {
            targetCount[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (targetCount[i] > 0) {
                res = Math.min(res, count[i]/targetCount[i]);
            }
        }
        return res;
    }
}
