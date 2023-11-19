package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 09/20/2020.
 * #1593 https://leetcode.com/problems/split-a-string-into-the-max-number-of-unique-substrings/
 */
public class SplitAStringIntoTheMaxNumberOfUniqueSubstrings {

    public int maxUniqueSplit(String s) {
        Set<String> visited = new HashSet<>();
        return dfs(0, s, visited);
    }

    private int dfs(int start, String s, Set<String> visited){
        if(start == s.length()){
            return 0;
        }
        int max = 0;
        for(int i = start; i < s.length(); i++){
            String cur = s.substring(start, i + 1);
            if(visited.contains(cur)){
                continue;
            }
            visited.add(cur);
            max = Math.max(max, 1 + dfs(i + 1, s, visited));
            visited.remove(cur);
        }
        return max;
    }
}
