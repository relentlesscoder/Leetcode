package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 11/05/2016.
 * #0090 https://leetcode.com/problems/subsets-ii/
 */
public class SubsetsII {

    // time O(n * 2^n), space O(n)
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        dfs1(0, new ArrayList<>(), nums, res);
        // dfs2(0, new ArrayList<>(), nums, res);
        return res;
    }

    private void dfs1(int i, List<Integer> path, int[] nums, List<List<Integer>> res) {
        // 枚举下一个要选的数
        res.add(new ArrayList<>(path));
        if (i == nums.length) {
            return;
        }
        // 在 [i, n - 1] 中选一个 nums[j]，注意选 nums[j] 意味着 [i, j - 1] 中的数都没有选
        for (int j = i; j < nums.length; j++) {
            // 如果 j > i，说明 nums[j - 1] 没有选，所有等于 nums[j - 1] 的数都不选
            if (j > i && nums[j] == nums[j - 1]) {
                continue;
            }
            path.add(nums[j]);
            dfs1(j + 1, path, nums, res);
            path.remove(path.size() - 1);
        }
    }

    private void dfs2(int i, List<Integer> path, int[] nums, List<List<Integer>> res) {
        int n = nums.length;
        if (i == n) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 选
        path.add(nums[i]);
        dfs2(i + 1, path, nums, res);
        path.remove(path.size() - 1);
        // 不选 - 如果不选当前的 nums[i] 那么后面所有等于 nums[i] 的也不能选
        int x = nums[i];
        while (i < n && nums[i] == x) {
            i++;
        }
        dfs2(i, path, nums, res);
    }
}
