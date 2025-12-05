package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 08/17/2025.
 * #2900 https://leetcode.com/problems/longest-unequal-adjacent-groups-subsequence-i/
 */
public class LongestUnequalAdjacentGroupsSubsequenceI {

    // time O(n), space O(n)
    public List<String> getLongestSubsequence(String[] words, int[] groups) {
        List<String> res = new ArrayList<>();
        int curr = groups[0];
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] == curr) {
                res.add(words[i]);
                curr = 1 - curr;
            }
        }
        return res;
    }
}
