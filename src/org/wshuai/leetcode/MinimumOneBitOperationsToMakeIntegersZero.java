package org.wshuai.leetcode;

/**
 * Created by Wei on 10/19/2020.
 * #1611 https://leetcode.com/problems/minimum-one-bit-operations-to-make-integers-zero/
 */
public class MinimumOneBitOperationsToMakeIntegersZero {

    // time O(log(n))
    // https://leetcode.com/problems/minimum-one-bit-operations-to-make-integers-zero/discuss/877741/C%2B%2B-solution-with-explanation
    public int minimumOneBitOperations(int n) {
        if(n <= 1){
            return n;
        }
        int bit = 0;
        while((1 << bit) <= n){
            bit++;
        }
        return ((1 << bit) - 1) - minimumOneBitOperations(n - (1 << (bit - 1)));
    }
}
