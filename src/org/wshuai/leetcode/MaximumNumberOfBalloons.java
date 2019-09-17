package org.wshuai.leetcode;

/**
 * Created by Wei on 9/17/19.
 * #1189 https://leetcode.com/problems/maximum-number-of-balloons/
 */
public class MaximumNumberOfBalloons {
    public int maxNumberOfBalloons(String text) {
        int[] count = new int[26];
        for(int i = 0; i < text.length(); i++){
            char c = text.charAt(i);
            if(c == 'b' ||
                    c == 'a' ||
                    c == 'l' ||
                    c == 'o' ||
                    c == 'n'){
                count[c-'a']++;
            }
        }
        int min = Integer.MAX_VALUE;
        min = Math.min(count[0], min);
        min = Math.min(count[1], min);
        min = Math.min(count['l'-'a']/2, min);
        min = Math.min(count['o'-'a']/2, min);
        min = Math.min(count['n'-'a'], min);
        return min;
    }
}
