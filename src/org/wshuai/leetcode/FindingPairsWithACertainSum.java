package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 05/22/2021.
 * #1865 https://leetcode.com/problems/finding-pairs-with-a-certain-sum/
 */
public class FindingPairsWithACertainSum {

    private Map<Integer, Integer> count = new HashMap<>();

    private int[] nums1, nums2;

    public FindingPairsWithACertainSum(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        for(int num : nums2){
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
    }

    public void add(int index, int val) {
        count.put(nums2[index], count.getOrDefault(nums2[index], 0) - 1);
        nums2[index] += val;
        count.put(nums2[index], count.getOrDefault(nums2[index], 0) + 1);
    }

    public int count(int tot) {
        int res = 0;
        for(int num : nums1){
            res += count.getOrDefault(tot - num, 0);
        }
        return res;
    }
}

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * FindSumPairs obj = new FindSumPairs(nums1, nums2);
 * obj.add(index,val);
 * int param_2 = obj.count(tot);
 */
