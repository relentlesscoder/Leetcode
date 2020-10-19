package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/19/2020.
 * #1624 https://leetcode.com/problems/largest-substring-between-two-equal-characters/
 */
public class LargestSubstringBetweenTwoEqualCharacters {

    // time O(n)
    public int maxLengthBetweenEqualCharacters(String s) {
        int res = -1, n = s.length();
        int[] lastIndex = new int[26];
        Arrays.fill(lastIndex, -1);
        for(int i = 0; i < n; i++){
            char c = s.charAt(i);
            if(lastIndex[c - 'a'] != -1){
                res = Math.max(res, i - lastIndex[c - 'a'] - 1);
            }else{
                lastIndex[c - 'a'] = i;
            }
        }
        return res;
    }
}
