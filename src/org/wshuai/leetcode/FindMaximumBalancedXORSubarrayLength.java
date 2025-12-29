package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 12/27/2025.
 * #3755 https://leetcode.cn/problems/find-maximum-balanced-xor-subarray-length/
 */
public class FindMaximumBalancedXORSubarrayLength {

    // time O(n), space O(n)
    public int maxBalancedSubarray(int[] nums) {
        // #0525和#2588的结合题，找到同时符合两个要求的最长子数组。
        // 本题还是维护一个哈希表来保存最早出现的前缀和的索引，唯一
        // 的难点是把两个状态压缩到一个长整型里作为哈希表的键。
        int res = 0, n = nums.length;
        Map<Long, Integer> map = new HashMap<>();
        map.put((long) n, -1);
        for (int i = 0, xor = 0, oe = n; i < n; i++) {
            xor ^= nums[i];
            oe += ((nums[i] & 1) == 1 ? 1 : -1);
            long key = ((long) xor << 20) + oe;
            if (map.containsKey(key)) {
                res = Math.max(res, i - map.get(key));
            }
            map.putIfAbsent(key, i);
        }
        return res;
    }
}
