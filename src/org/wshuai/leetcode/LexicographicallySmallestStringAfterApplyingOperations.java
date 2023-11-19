package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 10/19/2020.
 * #1625 https://leetcode.com/problems/lexicographically-smallest-string-after-applying-operations/
 */
public class LexicographicallySmallestStringAfterApplyingOperations {

    private String res;

    // time O(n^2), space O(n^3)
    public String findLexSmallestString(String s, int a, int b) {
        res = s;
        Set<String> visited = new HashSet<>();
        dfs(s, a, b, visited);
        return res;
    }

    private void dfs(String cur, int a, int b, Set<String> visited){
        if(visited.contains(cur)){
            return;
        }
        if(cur.compareTo(res) < 0){
            res = cur;
        }
        visited.add(cur);
        dfs(cur.substring(cur.length() - b) + cur.substring(0, cur.length() - b), a, b, visited);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < cur.length(); i++){
            if(i % 2 == 1){
                int d = ((cur.charAt(i) - '0') + a) % 10;
                sb.append((char)(d + '0'));
            }else{
                sb.append(cur.charAt(i));
            }
        }
        dfs(sb.toString(), a, b, visited);
    }
}
