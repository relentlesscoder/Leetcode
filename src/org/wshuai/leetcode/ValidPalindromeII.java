package org.wshuai.leetcode;

/**
 * Created by Wei on 9/6/19.
 * #680 https://leetcode.com/problems/valid-palindrome-ii/
 */
public class ValidPalindromeII {
    private int diff;
    private char[] arr;

    public boolean validPalindrome(String s) {
        diff = 0;
        arr = s.toCharArray();
        boolean valid = validPalindromeUtil(0, arr.length-1);
        return valid;
    }

    private boolean validPalindromeUtil(int left, int right){
        if(left >= right){
            return true;
        }
        if(arr[left] == arr[right]){
            return validPalindromeUtil(left+1, right-1);
        }else if(diff == 1){
            return false;
        }else{
            diff++;
            // if the current left does not equal to the current right
            // either remove the left or the right
            return validPalindromeUtil(left+1, right) || validPalindromeUtil(left, right-1);
        }
    }
}
