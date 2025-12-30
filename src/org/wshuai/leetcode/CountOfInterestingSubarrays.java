package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 09/21/2025.
 * #2845 https://leetcode.com/problems/count-of-interesting-subarrays/
 */
public class CountOfInterestingSubarrays {

    // time O(n), space O(n)
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        // #1590相似题
        // 设 cnt 为子数组中元素 nums[i] % m = k 的数量则 cnt % m = k。
        // 将 cnt 表示为数组前缀和的差则 (s[r] - s[l]) % m = k，等同于
        // ((s[r] - k) % m + m) % m = s[l] % m
        long res = 0;
        int n = nums.size();
        Map<Integer, Integer> prefix = new HashMap<>();
        prefix.put(0, 1);
        for (int i = 0, sum = 0; i < n; i++) {
            sum = (sum + (nums.get(i) % modulo == k ? 1 : 0)) % modulo;
            int key = (sum - k % modulo + modulo) % modulo;
            if (prefix.containsKey(key)) {
                res += prefix.get(key);
            }
            prefix.merge(sum, 1, Integer::sum);
        }
        return res;
    }
}
