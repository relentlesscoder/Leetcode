package org.wshuai.leetcode;

/**
 * Created by Wei on 12/28/2023.
 * #2164 https://leetcode.com/problems/sort-even-and-odd-indices-independently/
 */
public class SortEvenAndOddIndicesIndependently {

    // time O(n), space O(1)
    public int[] sortEvenOdd(int[] nums) {
        int n = nums.length;
        int[] res = new int[n], map = new int[101];
        for (int i = 0; i < n; i += 2) {
            map[nums[i]]++;
        }
        for (int i = 1, j = 0; i < 101; i++) {
            for (int k = map[i]; k > 0; k--) {
                res[j] = i;
                j += 2;
            }
        }
        map = new int[101];
        for (int i = 1; i < n; i += 2) {
            map[nums[i]]++;
        }
        for (int i = 100, j = 1; i > 0; i--) {
            for (int k = map[i]; k > 0; k--) {
                res[j] = i;
                j += 2;
            }
        }
        return res;
    }
}
