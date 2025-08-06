package org.wshuai.leetcode;

/**
 * Created by Wei on 08/05/2025.
 * #3507 https://leetcode.com/problems/minimum-pair-removal-to-sort-array-i/
 */
public class MinimumPairRemovalToSortArrayI {

    // time O(n^2), space O(n^2)
    public int minimumPairRemoval(int[] nums) {
        int res = 0, n = nums.length;
        int[] isSorted = checkSorted(nums);
        while (n > 1 && isSorted[0] == 0) {
            res++;
            int minIndex = isSorted[1];
            nums[minIndex] += nums[minIndex + 1];
            int[] copy = new int[n - 1];
            for (int i = 0, j = 0; j < n; j++) {
                if (j == minIndex + 1) {
                    continue;
                }
                copy[i++] = nums[j];
            }
            n--;
            nums = copy;
            isSorted = checkSorted(nums);
        }
        return res;
    }

    private int[] checkSorted(int[] nums) {
        int n = nums.length, minIndex = -1, min = 500000, isSorted = 1;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                isSorted = 0;
            }
            if (nums[i] + nums[i + 1] < min) {
                min = nums[i] + nums[i + 1];
                minIndex = i;
            }
        }
        return new int[] {isSorted, minIndex};
    }
}
