package org.wshuai.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 12/28/2025.
 * #2488 https://leetcode.com/problems/count-subarrays-with-median-k/
 */
public class CountSubarraysWithMedianK {

    // time O(n), space O(n)
    public int countSubarraysPrefixSumMap(int[] nums, int k) {
        // 子数组以 k 为中位数需满足以下条件:
        //   1. 组数组包含 k
        //   2. 如果子数组的大小为奇数，则数组中大于 k 的数的数量
        //      等于小于 k 的数量 - 后续简写为 g - l = 0。
        //   3. 如果子数组的大小为偶数，则 g - l = 1。
        // 预设 res = 1 是统计值为 k 的元素本身
        int res = 1, n = nums.length, idx = 0;
        // 遍历数组，找到值为 k 的索引 idx
        for (; idx < n; idx++) {
            if (nums[idx] == k) {
                break;
            }
        }
        // 维护一个哈希表，存从 idx - 1 开始的反向子数组的前缀和 g - l
        // 和它对应的数量。
        Map<Integer, Integer> prefix = new HashMap<>();
        // 先记录 idx 本身的值
        prefix.put(0, 1);
        // 从 idx - 1 反向遍历
        for (int i = idx - 1, diff = 0; i >= 0; i--) {
            diff += (nums[i] > k ? 1 : -1);
            // 如果当前 g - l 为 1 或者 0 则当前子数组 [i, idx] 为
            // 一个合法子数组。注意这里统计的实际上是所有以 idx 为结尾
            // 的合法子数组。
            // 示例1: nums = [2,5,1,4,3,6]，k = 1
            // i = 1 时 diff = 1 所以 [5, 1] 是一个合法子数组。
            if (diff == 1 || diff == 0) {
                res++;
            }
            // 更新哈希表
            prefix.merge(diff, 1, Integer::sum);
        }
        // 从 idx + 1 向后遍历，统计所有以 [idx + 1, n - 1] 为结尾的合
        // 法子数组 - 所以并不会与上面统计的子数组相重复。
        for (int i = idx + 1, diff = 0; i < n; i++) {
            // 计算 idx 右边的后缀 g - l 值
            diff += (nums[i] > k ? 1 : -1);
            // 如果后缀 g - l 值为 diff ，则前缀 g - l 值为 1 - diff
            // 的所有前缀子数组可以与之组合为一个长度为偶数的合法子数组。
            // 示例2: nums = [2,5,7,4,3,6]，k = 4
            // 子数组 [5,7,4,3]
            res += prefix.getOrDefault(1 - diff, 0);
            // 前缀 g - l 值为 - diff 的所有前缀子数组也可以与之组合为
            // 一个长度为奇数的合法子数组。
            // 示例2: nums = [2,5,1,4,3,6,7]，k = 4
            // 子数组 [1,4,3,6,7]
            res += prefix.getOrDefault(-diff, 0);
        }
        return res;
    }

    // time O(n), space O(n)
    public int countSubarraysPrefixSumArray(int[] nums, int k) {
        // 上面解的优化版 - 用数组取代哈希表
        int res = 1, n = nums.length, idx = 0;
        for (; idx < n; idx++) {
            if (nums[idx] == k) {
                break;
            }
        }
        // 因为 diff 的值域范围是 [-(n - 1), n - 1], 所以可以把每个
        // diff 值映射到正整数 diff + n。可以建立一个长度为 2 * n 的
        // 数组来存所有的 diff 值。
        int[] prefix = new int[n << 1];
        prefix[n] = 1;
        for (int i = idx - 1, diff = 0; i >= 0; i--) {
            diff += (nums[i] > k ? 1 : -1);
            if (diff == 1 || diff == 0) {
                res++;
            }
            prefix[n + diff]++;
        }
        for (int i = idx + 1, diff = 0; i < n; i++) {
            diff += (nums[i] > k ? 1 : -1);
            res += prefix[n + 1 - diff];
            res += prefix[n - diff];
        }
        return res;
    }
}
