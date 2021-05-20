package org.wshuai.leetcode;

/**
 * Created by Wei on 05/20/2021.
 * #1844 https://leetcode.com/problems/replace-all-digits-with-characters/
 */
public class ReplaceAllDigitsWithCharacters {

    // time O(n)
    public String replaceDigits(String s) {
        char[] arr = s.toCharArray();
        for(int i = 1; i < arr.length; i += 2){
            arr[i] = (char)(arr[i - 1] + (arr[i] - '0'));
        }
        return String.valueOf(arr);
    }
}
