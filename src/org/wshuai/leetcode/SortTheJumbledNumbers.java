package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 08/02/2025.
 * #2191 https://leetcode.com/problems/sort-the-jumbled-numbers/
 */
public class SortTheJumbledNumbers {

    // time O(n * log(n)), space O(n)
    public int[] sortJumbled(int[] mapping, int[] nums) {
        int n = nums.length;
        int[][] sorted = new int[n][3];
        for (int i = 0; i < n; i++) {
            char[] arr = Integer.toString(nums[i]).toCharArray();
            for (int j = 0; j < arr.length; j++) {
                arr[j] = (char)('0' + mapping[arr[j] - '0']);
            }
            sorted[i] = new int[]{i, nums[i], Integer.parseInt(String.valueOf(arr))};
        }
        Arrays.sort(sorted, (a, b) ->
                a[2] == b[2] ? a[0] - b[0] : a[2] - b[2]);
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = sorted[i][1];
        }
        return res;
    }
}
