package org.wshuai.leetcode;

/**
 * Created by Wei on 8/21/19.
 * #744 https://leetcode.com/problems/find-smallest-letter-greater-than-target/
 */
public class FindSmallestLetterGreaterThanTarget {

    public char nextGreatestLetterBinarySearch(char[] letters, char target) {
        int lo = 0;
        int hi = letters.length;
        while(lo < hi){
            int mi = (lo + hi)/2;
            if(letters[mi] <= target){
                lo = mi + 1;
            }else{
                hi = mi;
            }
        }
        return letters[lo % letters.length];
    }

    public char nextGreatestLetterLinearScan(char[] letters, char target) {
        for(char c: letters){
            if(c > target){
                return c;
            }
        }
        return letters[0];
    }
}
