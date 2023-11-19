package org.wshuai.leetcode;

/**
 * Created by Wei on 05/16/2021.
 * #1796 https://leetcode.com/problems/second-largest-digit-in-a-string/
 */
public class SecondLargestDigitInAString {

    // time O(n)
    public int secondHighest(String s) {
        int largest = Integer.MIN_VALUE, secondLargest = Integer.MIN_VALUE;
        for(char c : s.toCharArray()){
            if(Character.isDigit(c)){
                int d = c - '0';
                if(d >= largest){
                    secondLargest = d == largest ? secondLargest : largest; // avoid duplicates
                    largest = d;
                }else if(d > secondLargest){
                    secondLargest = d;
                }
            }
        }
        return secondLargest == Integer.MIN_VALUE ? -1 : secondLargest;
    }
}
