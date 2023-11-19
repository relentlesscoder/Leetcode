package org.wshuai.leetcode;

/**
 * Created by Wei on 04/27/2021.
 * #1822 https://leetcode.com/problems/sign-of-the-product-of-an-array/
 */
public class SignOfTheProductOfAnArray {

    // time O(n)
    public int arraySign(int[] nums) {
        int negative = 0;
        for(int num : nums){
            if(num == 0){
                return 0;
            }
            negative += num < 0 ? 1 : 0;
        }
        return negative % 2 == 0 ? 1 : -1;
    }
}
