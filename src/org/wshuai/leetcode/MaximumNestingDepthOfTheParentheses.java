package org.wshuai.leetcode;

/**
 * Created by Wei on 10/12/2020.
 * #1614 https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/
 */
public class MaximumNestingDepthOfTheParentheses {

    // time O(n)
    public int maxDepth(String s) {
        int depth = 0, cur = 0;
        for(char c : s.toCharArray()){
            if(c != '(' && c != ')'){
                continue;
            }
            cur += c == '(' ? 1 : -1;
            depth = Math.max(cur, depth);
        }
        return depth;
    }
}
