package org.wshuai.leetcode;

/**
 * Created by Wei on 01/09/2024.
 * #2455 https://leetcode.com/problems/average-value-of-even-numbers-that-are-divisible-by-three/
 */
public class AverageValueOfEvenNumbersThatAreDivisibleByThree {

    // time O(n), space O(1)
    public int averageValue(int[] nums) {
        int sum = 0, count = 0;
        for (int num : nums) {
            if (num % 6 == 0) {
                sum += num;
                count += 1;
            }
        }
        return count == 0 ? 0 : sum / count;
    }
}
