package org.wshuai.leetcode;

/**
 * Created by Wei on 05/22/2021.
 * #1837 https://leetcode.com/problems/sum-of-digits-in-base-k/
 */
public class SumOfDigitsInBaseK {

    // time O(log(n))
    public int sumBase(int n, int k) {
        int sum = 0;
        while(n != 0){
            sum += n % k;
            n /= k;
        }
        return sum;
    }
}
