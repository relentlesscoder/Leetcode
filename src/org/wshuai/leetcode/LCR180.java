package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 12/03/2025.
 * #LCR180 https://leetcode.cn/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/
 */
public class LCR180 {

    // time O(target), space O(1)
    public int[][] fileCombination(int target) {
        List<int[]> res = new ArrayList<>();
        for (int i = 1, j = 1, sum = 0; j <= (target + 1) / 2; i++) {
            sum += i;
            while (sum > target) {
                sum -= j;
                j++;
            }
            if (sum == target) {
                int[] arr = new int[i - j + 1];
                for (int k = 0; k < arr.length; k++) {
                    arr[k] = k + j;
                }
                res.add(arr);
            }
        }
        return res.toArray(new int[0][]);
    }
}
