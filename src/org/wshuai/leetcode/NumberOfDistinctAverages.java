package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 09/04/2023.
 * #2465 https://leetcode.com/problems/number-of-distinct-averages/
 */
public class NumberOfDistinctAverages {

    // time O(n*log(n)), space O(n)
    public int distinctAverages(int[] nums) {
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        Set<Integer> averages = new HashSet<>();
        while (left < right) {
            int max = nums[left++];
            int min = nums[right--];
            averages.add(max + min);
        }
        return averages.size();
    }
}
