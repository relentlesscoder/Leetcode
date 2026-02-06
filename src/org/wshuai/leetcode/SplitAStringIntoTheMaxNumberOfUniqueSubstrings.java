package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 09/20/2020.
 * #1593 https://leetcode.com/problems/split-a-string-into-the-max-number-of-unique-substrings/
 */
public class SplitAStringIntoTheMaxNumberOfUniqueSubstrings {

    private int res = 0;

    // time O(2^n), space O(n)
    public int maxUniqueSplit(String s) {
        res = 0;
        dfs(0, new HashSet<>(), s);
        return res;
    }

    private void dfs(int i, Set<String> path, String s) {
        if (i == s.length()) {
            res = Math.max(res, path.size());
            return;
        }
        for (int j = i; j < s.length(); j++) {
            String str = s.substring(i, j + 1);
            if (!path.contains(str)) {
                path.add(str);
                dfs(j + 1, path, s);
                path.remove(str);
            }
        }
    }
}
