package org.wshuai.leetcode;

/**
 * Created by Wei on 12/17/2023.
 * #2475 https://leetcode.com/problems/number-of-unequal-triplets-in-array/
 */
public class NumberOfUnequalTripletsInArray {

    // time O(n), space O(1)
    public int unequalTriplets(int[] nums) {
        int[] count = new int[1_001];
        int triplets = 0, pairs = 0;
        for (int i = 0; i < nums.length; i++) { // for each nums[i], find how many pairs on its left we can form an unequal triplets
            triplets += pairs - count[nums[i]] * (i - count[nums[i]]); // find pairs that does not contain nums[i] - total pairs excluding pairs that contain nums[i]
            pairs += i - count[nums[i]]; // find all elements on the left that can form an unequal pair with nums[i]
            count[nums[i]]++;
        }
        return triplets;
    }
}
