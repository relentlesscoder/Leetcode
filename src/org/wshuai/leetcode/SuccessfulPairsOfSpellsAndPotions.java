package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 08/06/2025.
 * #2300 https://leetcode.com/problems/successful-pairs-of-spells-and-potions/
 */
public class SuccessfulPairsOfSpellsAndPotions {

    // time O((n + m) * log(m)), space O(log(m))
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length, m = potions.length;
        int[] res = new int[n];
        Arrays.sort(potions);
        for (int i = 0; i < n; i++) {
            long minPotion = (success + spells[i] - 1) / spells[i];
            if (minPotion > potions[m - 1]) {
                res[i] = 0;
                continue;
            }
            res[i] = search(potions, (int)minPotion);
        }
        return res;
    }

    private int search(int[] nums, int target) {
        // set high to m so that when all nums are not satisfying the last index will stop at m
        int m = nums.length, low = 0, high = m;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return m - low;
    }

    // time O(m * log(m) + n * log(n)), space O(n + log(m))
    public int[] successfulPairsSorting(int[] spells, int[] potions, long success) {
        int n = spells.length, m = potions.length;
        int[] res = new int[n];
        int[][] sortedSpells = new int[n][2];
        for (int i = 0; i < n; i++) {
            sortedSpells[i] = new int[]{spells[i], i};
        }
        Arrays.sort(potions);
        Arrays.sort(sortedSpells, (a, b) -> a[0] - b[0]);
        for (int i = 0, j = m - 1; i < n; i++) {
            int[] spell = sortedSpells[i];
            while (j >= 0 && (long) spell[0] * potions[j] >= success) {
                j--;
            }
            res[spell[1]] = m - (j + 1);
        }
        return res;
    }
}
