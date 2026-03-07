package org.wshuai.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 12/30/2025.
 * #3729 https://leetcode.com/problems/count-distinct-subarrays-divisible-by-k-in-sorted-array/
 */
public class CountDistinctSubarraysDivisibleByKInSortedArray {

    // time O(n), space O(n)
    public long numGoodSubarrays(int[] nums, int k) {
        // 直接在遍历的时候去重。
        // 例如在 nums = [1,2,2,3,3,3] 中找 [3,3]，只要限定子数组的左端点在第一个
        // 3 或者更靠左的位置，就不会错误地统计两个 [3,3] 子数组了。换句话说，对于连
        // 续相同元素段，我们要保证哈希表暂时不包含这一段对应的前缀和，等遍历完这一段，
        // 再把对应的前缀和加到哈希表中。
        // 例如 nums = [1,2,2,3,3,3]，在遍历 3 的过程中，哈希表只保存 [1,2,2] 中
        // 的前缀和，此时在哈希表中查询，就相当于限定子数组的左端点在第一个 3 或者更靠
        // 左的位置。这样就能保证统计的子数组无重复。
        long res = 0, sum = 0;
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        // last 是上一个连续相同段的起始下标
        for (int i = 0, last = 0; i < n; i++) {
            int x = nums[i];
            if (i > 0 && x != nums[i - 1]) {
                // 上一个连续相同段结束，可以把上一段对应的前缀和添加到哈希表里
                long s = sum;
                // i - last 是相同段的长度
                for (int t = i - last; t > 0; t--) {
                    map.merge((int) (s % k), 1, Integer::sum);
                    s -= nums[i - 1]; // 实时计算前缀和
                }
                // 更新 last 的位置到当前索引
                last = i;
            }
            sum += x;
            int mod = (int) (sum % k);
            res += map.getOrDefault(mod, 0);
        }
        return res;
    }

    // time O(n), space O(n)
    public long numGoodSubarraysDeduplicateAfter(int[] nums, int k) {
        long res = 0, sum = 0;
        int n = nums.length;
        // #0974 找到所有和可被 k 整除的子数组的数量
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < n; i++) { // O(n)
            sum += nums[i];
            int mod = (int) (sum % k);
            res += map.getOrDefault(mod, 0);
            map.merge(mod, 1, Integer::sum);
        }
        // 给找到的子数组去重
        for (int i = 0, start = 0; i < n; i++) {
            int x = nums[i];
            if (i < n - 1 && x == nums[i + 1]) {
                continue;
            }
            // 遍历到了连续相同元素段的末尾
            int size = i - start + 1; // 这一段的长度
            for (int sz = 1; sz <= size; sz++) {
                // 长为 sz 的子数组元素和能被 k 整除。一共有 size - sz + 1
                // 个长为 sz 的子数组，其中有 size - sz 个重复的。
                // 示例1: subarray [2,2,2,2,2,2,2], k = 4
                //   [2,2] 可以整除 4 ，子数组中共含有 7 - 2 + 1 = 6 个长
                //   为 2 的子数组。去掉其中重复的 5 个。
                //   [2,2,2,2] 可以整除 4 ，子数组中共含有 7 - 4 + 1 = 4
                //   个长为 4 的子数组。去掉其中重复的 3 个。
                //   [2,2,2,2,2,2] 可以整除 4 ，子数组中共含有 7 - 6 + 1
                //   = 2 个长为 6 的子数组。去掉其中重复的 1 个。
                // 总共去掉 9 个重复的子数组。
                if ((long) x * sz % k == 0) {
                    res -= size - sz;
                }
            }
            start = i + 1;
        }
        return res;
    }
}
