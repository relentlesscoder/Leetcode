package org.wshuai.leetcode;

/**
 * Created by Wei on 8/7/19.
 * #1085 https://leetcode.com/problems/sum-of-digits-in-the-minimum-number/
 */
public class SumOfDigitsInTheMinimumNumber {
    public int sumOfDigits(int[] A) {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < A.length; i++){
            min = A[i] < min ? A[i] : min;
        }
        int sum = 0;
        while(min > 0){
            sum += min % 10;
            min /= 10;
        }
        return sum % 2 == 0 ? 1 : 0;
    }
}
