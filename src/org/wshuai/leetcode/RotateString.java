package org.wshuai.leetcode;

/**
 * Created by Wei on 8/31/2019.
 * #796 https://leetcode.com/problems/rotate-string/
 */
public class RotateString {
    public boolean rotateString(String A, String B) {
        if(A == null || B == null){
            return false;
        }
        if(A.length() != B.length()){
            return false;
        }
        if(A.length() == 0){
            return true;
        }
        char first = A.charAt(0);
        for(int i = 0; i < B.length(); i++){
            if(B.charAt(i) != first){
                continue;
            }
            String val = B.substring(i, B.length()) + B.substring(0, i);
            if(val.equals(A)){
                return true;
            }
        }
        return false;
    }
}
