package org.wshuai.leetcode;

/**
 * Created by Wei on 03/08/2021.
 * #1784 Check if Binary String Has at Most One Segment of Ones
 */
public class CheckIfBinaryStringHasAtMostOneSegmentOfOnes {

    // time O(n)
    public boolean checkOnesSegment(String s) {
        for(int i = 1; i < s.length(); i++){
            if(s.charAt(i - 1) == '0' && s.charAt(i) == '1'){
                return false;
            }
        }
        return true;
    }
}
