package org.wshuai.leetcode;

import java.util.Random;

/**
 * Created by Wei on 09/18/2019.
 * #0528 https://leetcode.com/problems/random-pick-with-weight/
 */
public class RandomPickWithWeight {

    private int sum;
    private int[] prefixSum;
    private Random random;

    public RandomPickWithWeight(int[] w) {
        sum = 0;
        prefixSum = new int[w.length];
        random = new Random();
        for (int i = 0; i < w.length; i++) {
            sum += w[i];
            prefixSum[i] = sum;
        }
    }

    public int pickIndex() {
        int pick = random.nextInt(sum) + 1;
        return binarySearch(prefixSum, pick);
    }

    // time O(log(n)), space O(n)
    private int binarySearch(int[] nums, int val) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] >= val) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

}
