package org.wshuai.leetcode;

/**
 * Created by Wei on 8/21/19.
 * #925 https://leetcode.com/problems/long-pressed-name/
 */
public class LongPressedName {
    public boolean isLongPressedName(String name, String typed) {
        char[] arr1 = name.toCharArray();
        char[] arr2 = typed.toCharArray();
        int i = 0;
        int j = 0;
        while(j < arr2.length){
            // i might reach the end first so need to check the bound
            if(i < arr1.length && arr1[i] == arr2[j]){
                i++;
                j++;
            // i need to wait until j go through all long pressed characters
            }else if(i > 0 && arr1[i-1] == arr2[j]){
                j++;
            }else{
                return false;
            }
        }
        return i == arr1.length && j == arr2.length;
    }
}
