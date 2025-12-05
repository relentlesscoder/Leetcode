package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/05/2025.
 * #2234 https://leetcode.com/problems/maximum-total-beauty-of-the-gardens/
 */
public class MaximumTotalBeautyOfTheGardens {

    // time O(n * log(n)), space O(n)
    public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
        int n = flowers.length;
        Arrays.sort(flowers);
        // 构建前缀和数组
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + flowers[i];
        }
        long res = 0;
        // 统计已完成的花园的数量
        int completed = 0;
        for (int f : flowers) {
            if (f >= target) {
                completed++;
            }
        }
        for (; completed <= n; completed++) {
            // 注意需要在这里先执行一次不使用任何花的操作，以计算把所有的花都用于增加未完成
            // 花园最小值得到的分数。
            // 示例1:
            // flowers = [13]，newFlowers = 18，target = 15，full = 9，partial = 2
            newFlowers -= (completed == 0 ? 0 : Math.max(0, target - flowers[n - completed]));
            if (newFlowers < 0) {
                break;
            }
            // 二分查找一个mid, 使得剩余的花足够将所有在区间[0, mid]中的值变成nums[mid]
            int low = 0, high = n - completed - 1;
            while (low < high) {
                int mid = low + (high - low + 1) / 2;
                if ((long) flowers[mid] * (mid + 1) - prefix[mid + 1] <= newFlowers) {
                    low = mid;
                } else {
                    high = mid - 1;
                }
            }
            long uncompleted = 0;
            if (high != -1) {
                // cost是把所有在区间[0, low]中的值变成nums[low]所需要使用的花的数量
                long cost = (long) flowers[low] * (low + 1) - prefix[low + 1];
                // (newFlowers - cost) / (low + 1) 是在上述的数量之外还可以用剩余
                // 的花均摊得到的在区间[0, low]中未完成花园最小值的增加。这个值加上
                // flowers[low]就是当前的所有未完成花园最小值
                uncompleted = Math.min(flowers[low] + (newFlowers - cost) / (low + 1), target - 1);
            }
            res = Math.max(res, (long) completed * full + uncompleted * partial);
        }
        return res;
    }
}
