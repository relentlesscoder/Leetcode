package org.wshuai.leetcode;

/**
 * Created by Wei on 05/19/2021.
 * #1816 https://leetcode.com/problems/truncate-sentence/
 */
public class TruncateSentence {

    // time O(n)
    public String truncateSentence(String s, int k) {
        int i = 0, j = 0;
        while(i < s.length() && j < k){
            if(s.charAt(i++) == ' '){
                j++;
            }
        }
        return i < s.length() ? s.substring(0, i - 1) : s;
    }
}
