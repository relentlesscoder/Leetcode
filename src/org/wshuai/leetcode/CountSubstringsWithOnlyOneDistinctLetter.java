package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 9/10/2019.
 * #1180 https://leetcode.com/problems/count-substrings-with-only-one-distinct-letter/
 */
public class CountSubstringsWithOnlyOneDistinctLetter {
    public int countLetters(String S) {
        List<Integer> lst = new ArrayList<>();
        // add a postfix to handle the last character count
        S += "#";
        int count = 0;
        char curr = S.charAt(0);
        for(int i = 0; i < S.length(); i++){
            char c = S.charAt(i);
            if(c == curr){
                count++;
            }else{
                lst.add(count);
                count = 1;
                curr = c;
            }
        }
        int res = 0;
        for(int i: lst){
            while(i > 0){
                res += i--;
            }
        }
        return res;
    }
}
