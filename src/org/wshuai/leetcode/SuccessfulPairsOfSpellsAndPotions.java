package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 08/06/2025.
 * #2300 https://leetcode.com/problems/successful-pairs-of-spells-and-potions/
 */
public class SuccessfulPairsOfSpellsAndPotions {

    // time O(n + m + max), space O(max)
    public int[] successfulPairsPrefixSum(int[] spells, int[] potions, long success) {
        // 统计药水强度的频率，并建立一个基于药水强度的后缀和数组
        int max = 0;
        for (int p : potions) { // O(m)
            max = Math.max(max, p);
        }
        int[] freq = new int[max + 1];
        for (int p : potions) { // O(m)
            freq[p]++;
        }
        // 原地计算后缀和，freq[i]现在代表药水强度大于等于i的药水的总数量
        for (int i = max - 1; i >= 0; i--) { // O(max)
            freq[i] += freq[i + 1];
        }
        for (int i = 0; i < spells.length; i++) { // O(n)
            // 对当前咒语计算要达到成功阈值所需的最小药水强度，在后缀和数组中
            // 可以在线性时间内找到大于等于最小强度的总数量。
            long lowerBound = (success + spells[i] - 1) / spells[i];
            spells[i] = lowerBound <= max ? freq[(int) lowerBound] : 0;
        }
        return spells;
    }

    // time O((n + m) * log(m)), space O(log(m))
    public int[] successfulPairsBinarySearch(int[] spells, int[] potions, long success) {
        int n = spells.length, m = potions.length;
        int[] res = new int[n];
        Arrays.sort(potions); // O(m * log(m))
        for (int i = 0; i < n; i++) { // O(n)
            // 算出要达到成功的药水最低强度
            long minPotion = (success + spells[i] - 1) / spells[i];
            // 加一个判断防止整型越界以及性能优化
            if (minPotion > potions[m - 1]) {
                res[i] = 0;
                continue;
            }
            // 二分查找最低强度
            res[i] = m - search(potions, (int) minPotion); // O(log(m))
        }
        return res;
    }

    private int search(int[] nums, int target) {
        int m = nums.length, low = 0, high = m;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
