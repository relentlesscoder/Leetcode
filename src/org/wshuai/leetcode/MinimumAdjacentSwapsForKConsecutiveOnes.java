package org.wshuai.leetcode;

/**
 * Created by Wei on 01/01/2026.
 * #1703 https://leetcode.com/problems/minimum-adjacent-swaps-for-k-consecutive-ones/
 */
public class MinimumAdjacentSwapsForKConsecutiveOnes {

    // time O(n), space O(m)
    public int minMoves(int[] nums, int k) {
        // 将两个相邻 1 之间的 0 当作它们的距离则可以把原数组转化成定长为 k 的子数组
        // 问题，比如数组 [1,0,0,1,0,1,0,0,1,1] 可以转化为 [1,3,4,6,6] 。题目要
        // 求我们把相邻的 k 个 1 移动到一起形成连续的值为 1 的子数组，必然需要选择其
        // 中一个 1 而把其他的 1 向他移动。所以转化后这个要求就转化为求定长为 k 的子
        // 数组中所有元素变成相同的元素需要的操作数 (#2968)。因为转化后的数组显然是
        // 非递减数组，我们可以利用定长滑窗和前缀和求解。
        int res = Integer.MAX_VALUE, n = nums.length, m = 0;
        // 转化原数组
        for (int i = 0, sum = 0; i < n; i++) {
            if (nums[i] == 1) {
                nums[i] += sum; // 加上当前距离
                m++; // 统计 1 的个数
            } else {
                sum++; // 距离加 1
            }
        }
        // 同时构建距离数组和前缀和数组
        int[] dist = new int[m], prefix = new int[m + 1];
        for (int i = 0, j = 0; i < n; i++) {
            if (nums[i] > 0) {
                dist[j] = nums[i];
                prefix[j + 1] = prefix[j] + dist[j++];
            }
        }
        // 滑窗计算每个定长为 k 的窗口的操作和
        for (int r = k - 1; r < m; r++) {
            int l = r - k + 1;
            int mid = l + (r - l) / 2;
            int cost = dist[mid] * (mid - l + 1) - (prefix[mid + 1] - prefix[l]);
            cost += prefix[r + 1] - prefix[mid + 1] - dist[mid] * (r - mid);
            res = Math.min(res, cost);
        }
        return res;
    }
}
