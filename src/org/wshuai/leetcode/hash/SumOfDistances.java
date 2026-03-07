package org.wshuai.leetcode.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 08/11/2025.
 * #2615 https://leetcode.com/problems/sum-of-distances/
 */
public class SumOfDistances {

    // time O(n), space O(n)
    public long[] distancePrefixSum1(int[] nums) {
        // 与下解同样思路，只是用一个哈希表把元素值对应的所有索引存下来然后对
        // 每个元素遍历哈希表中与之对应的所有索引来计算。
        int n = nums.length;
        long[] res = new long[n];
        Map<Integer, List<Integer>> idxMap = new HashMap<>();
        Map<Integer, Long> sumMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            idxMap.computeIfAbsent(x, k -> new ArrayList<>()).add(i);
            sumMap.merge(x, (long) i, Long::sum);
        }
        for (int key : idxMap.keySet()) {
            List<Integer> list = idxMap.get(key);
            int size = list.size();
            if (size == 1) {
                continue;
            }
            long sum = 0L;
            for (int i = 0; i < size; i++) {
                int idx = list.get(i);
                sum += idx;
                // 计算 i 及其左边
                res[idx] = (long) idx * (i + 1) - sum;
                // 计算 i 右边
                res[idx] += sumMap.get(key) - sum - (long) idx * (size - i - 1);
            }
        }
        return res;
    }

    // time O(n), space O(n)
    public long[] distancePrefixSum2(int[] nums) {
        // #1685相似题，对每个位置我们需要知道数组中等于当前元素的:
        //   1. 总索引和前缀索引和
        //   2. 总数量和前缀数量和
        int n = nums.length;
        long[] res = new long[n];
        // 对数组中每个元素计算总数量和总索引和
        Map<Integer, Long> sumMap = new HashMap<>();
        Map<Integer, Integer> totalMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            sumMap.merge(nums[i], (long) i, Long::sum);
            totalMap.merge(nums[i], 1, Integer::sum);
        }
        // 维护两个哈希表分别存元素值及其对应的前缀索引和和前缀数量和
        Map<Integer, Long> prefixMap = new HashMap<>();
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            // 数组中只有一个元素等于当前元素值
            if (totalMap.get(x) == 1) {
                continue;
            }
            // 计算前缀索引和
            long sum = prefixMap.merge(x, (long) i, Long::sum);
            // 计算前缀数量和
            int cnt = countMap.merge(x, 1, Integer::sum);
            // 计算 i 及其左边
            res[i] = (long) i * cnt - sum;
            // 计算 i 右边
            res[i] += sumMap.get(x) - sum - (long) i * (totalMap.get(x) - cnt);
        }
        return res;
    }
}
