package org.wshuai.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 02/07/2026.
 * #3437 https://leetcode.com/problems/permutations-iii/
 */
public class PermutationsIII {

    // time O(n * n!), space O(n)
    public int[][] permute(int n) {
        List<int[]> list = new ArrayList<>();
        dfs(0, new int[n], 0, list);
        int[][] res = new int[list.size()][n];
        Arrays.setAll(res, i -> list.get(i));
        return res;
    }

    private void dfs(int i, int[] nums, int used, List<int[]> list) {
        int n = nums.length;
        if (i == n) {
            int[] ans = new int[n];
            Arrays.setAll(ans, x -> nums[x]);
            list.add(ans);
            return;
        }
        if (i == 0) { // 第一个数所有的数都可以用
            for (int j = 1; j <= n; j++) {
                process(i, j, used, nums, list);
            }
        } else if (nums[i - 1] % 2 == 0) { // 不是第一个数且前面的数是偶数则只能选奇数
            for (int j = 1; j <= n; j += 2) {
                process(i, j, used, nums, list);
            }
        } else {
            for (int j = 2; j <= n; j += 2) { // 不是第一个数且前面的数是奇数则只能选偶数
                process(i, j, used, nums, list);
            }
        }
    }

    private void process(int i, int j, int used, int[] nums, List<int[]> list) {
        if (((1 << j) & used) == 0) {
            used |= (1 << j);
            nums[i] = j;
            dfs(i + 1, nums, used, list);
            // 不用恢复现场因为 used 每次都是作为参数传进来的而 nums[i] 每次都是直接覆盖。
        }
    }
}
