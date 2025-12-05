package org.wshuai.leetcode;

/**
 * Created by Wei on 01/01/2024.
 * #2562 https://leetcode.com/problems/find-the-array-concatenation-value/
 */
public class FindTheArrayConcatenationValue {

    // time O(n), space O(1)
    public long findTheArrayConcVal(int[] nums) {
        long res = 0;
        int n = nums.length, i = 0, j = n - 1;
        while (i <= j) {
            if (i == j) {
                res += nums[i];
            } else {
                int v1 = nums[i], v2 = nums[j];
                while (v2 > 0) {
                    v1 *= 10;
                    v2 /= 10;
                }
                res += v1 + nums[j];
            }
            i++;
            j--;
        }
        return res;
    }
}
