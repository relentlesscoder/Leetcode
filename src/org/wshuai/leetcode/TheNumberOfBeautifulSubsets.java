package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 10/01/2025.
 * #2597 https://leetcode.com/problems/the-number-of-beautiful-subsets/
 */
public class TheNumberOfBeautifulSubsets {

    private int res = 0;

    // time O(2^n), space O(n)
    public int beautifulSubsets(int[] nums, int k) {
        res = 0;
        // 哈希表存值到值在数组中所有的索引的掩码的映射
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int mask = map.getOrDefault(nums[i], 0);
            mask |= (1 << i);
            map.put(nums[i], mask);
        }
        dfs(0, 0, map, nums, k);
        return res;
    }

    private void dfs(int i, int mask, Map<Integer, Integer> map, int[] nums, int k) {
        if (i == nums.length) {
            res += mask > 0 ? 1 : 0;
            return;
        }
        // 不选当前元素
        dfs(i + 1, mask, map, nums, k);
        int x = nums[i] + k, y = nums[i] - k;
        // 位运算判断之前有没有任何一个 nums[i] + k 或 nums[i] - k 已经被选了
        if ((map.getOrDefault(x, 0) & mask) == 0
                && (map.getOrDefault(y, 0) & mask) == 0) {
            // 如果都没被选则当前元素可选
            dfs(i + 1, mask | (1 << i), map, nums, k);
        }
    }

    // time O(2^n), space O(n)
    public int beautifulSubsetsBitMask(int[] nums, int k) {
        int res = 0, n = nums.length, mask = 1 << n;
        // 构造数组中元素关系的邻接表: 如果两个元素的绝对差为 k ，则它们之间有一条边。
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) == k) {
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }
        // 对所有可能的选择
        for (int i = 1; i < mask; i++) {
            if (isBeautiful(i, nums, adj)) {
                res++;
            }
        }
        return res;
    }

    private boolean isBeautiful(int mask, int[] nums, List<Integer>[] adj) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 当前索引 i 的元素没被选
            if (((1 << i) & mask) == 0) {
                continue;
            }
            // 如果 i 被选了，用邻接表判断他相邻的 (绝对差等于 k ) 任意元素有没有被选。
            for (int j = 0; j < adj[i].size(); j++) {
                if (((1 << adj[i].get(j)) & mask) != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
