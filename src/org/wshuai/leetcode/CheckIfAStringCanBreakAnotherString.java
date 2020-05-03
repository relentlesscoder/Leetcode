package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 05/03/2020.
 * #1433 https://leetcode.com/problems/check-if-a-string-can-break-another-string/
 */
public class CheckIfAStringCanBreakAnotherString {
    // time O(n*log(n))
    public boolean checkIfCanBreak(String s1, String s2) {
        char[] arr1 = s1.toCharArray(), arr2 = s2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return canBreak(arr1, arr2) || canBreak(arr2, arr1);
    }

    private boolean canBreak(char[] arr1, char[] arr2){
        for(int i = 0; i < arr1.length; i++){
            if(arr1[i] < arr2[i]){
                return false;
            }
        }
        return true;
    }
}
