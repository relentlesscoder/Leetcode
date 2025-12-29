package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 12/28/2025.
 * #3728 https://leetcode.com/problems/stable-subarrays-with-equal-boundary-and-interior-sum/
 */
public class StableSubarraysWithEqualBoundaryAndInteriorSum {

    private record Pair(int val, long sum) { }

    // time O(n), space O(n)
    public long countStableSubarrays(int[] capacity) {
        // https://leetcode.cn/problems/stable-subarrays-with-equal-boundary-and-interior-sum/solutions/3815641/qian-zhui-he-yu-ha-xi-biao-shi-zi-bian-x-d6vf/
        // 题目要求的式子，等价于:
        //   capacity[l] = capacity[r] = prefix[r] − prefix[l + 1]
        // 这等价于如下两个式子同时成立:
        //   capacity[l] = capacity[r]
        //   capacity[l] + prefix[l + 1] = prefix[r]
        // 且满足 r - l + 1 >= 3
        long res = 0, sum = capacity[0];
        int n = capacity.length;
        Map<Pair, Integer> map = new HashMap<>();
        for (int r = 1; r < n; r++) {
            // 当前前缀和 sum 是不包含 capacity[i] 的所以 sum = prefix[r]。这个
            // pair 里的两个值分别对应上面两个等式的右边部分 - capacity[r] 和 prefix[r]。
            res += map.getOrDefault(new Pair(capacity[r], sum), 0);
            // 在计算完当前位置的子数组数目之后再把 i - 1 位置的值更新到哈希表里，这样
            // 计算时哈希表里的值是 i - 2 以及之前的因此可以保证子数组至少有三个数。
            // 此 pair 里的两个值分别对应上面两个等式左边部分 - capacity[l] 和
            // capacity[l] + prefix[l + 1]。注意对于索引 l = r - 1 来说，sum 就是
            // prefix[l + 1]。
            map.merge(new Pair(capacity[r - 1], capacity[r - 1] + sum), 1, Integer::sum);
            // 先计算后更新前缀和
            sum += capacity[r];
        }
        return res;
    }
}
