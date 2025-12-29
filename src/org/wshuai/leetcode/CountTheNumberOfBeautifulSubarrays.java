package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 12/26/2025.
 * #2588 https://leetcode.com/problems/count-the-number-of-beautiful-subarrays/
 */
public class CountTheNumberOfBeautifulSubarrays {

    // time O(n), space O(n)
    public long beautifulSubarrays(int[] nums) {
        // 如果两个前缀和的异或值相同，则表示它们的差的子数组所有位上的1都可以
        // 两两抵消。则这样的子数组是符合要求的。
        // 示例1:
        //  0 4 100
        //  1 3 011
        //  2 1 001
        //  3 2 010
        // 前缀和[0,0]的xor为100，前缀和[0,3]的xor也为100. 所以子数组[1,3]
        // 是一个合法子数组。
        long res = 0;
        int n = nums.length;
        // 哈希表的键是前缀异或和，值为此键对应的前缀子数组的数量
        Map<Integer, Integer> prefix = new HashMap<>();
        prefix.put(0, 1);
        for (int i = 0, xor = 0; i < n; i++) {
            xor ^= nums[i];
            res += prefix.getOrDefault(xor, 0);
            prefix.merge(xor, 1, Integer::sum);
        }
        return res;
    }
}
