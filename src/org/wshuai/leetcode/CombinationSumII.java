package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 10/28/2016.
 * #0040 https://leetcode.com/problems/combination-sum-ii/
 */
public class CombinationSumII {

    // time O(n * 2^n), space O(n)
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // #0090
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        dfs1(0, target, new ArrayList<>(), candidates, res);
        // dfs2(0, target, new ArrayList<>(), candidates, res);
        return res;
    }

    private void dfs1(int i, int target, List<Integer> path, int[] nums, List<List<Integer>> res) {
        // 枚举下一个要选的数
        if (target == 0) {
            res.add(new ArrayList<>(path));
        }
        if (i == nums.length || target < nums[i]) {
            return;
        }
        // 在 [i, n - 1] 中选一个 nums[j]，注意选 nums[j] 意味着 [i, j - 1] 中的数都没有选
        for (int j = i; j < nums.length; j++) {
            // 如果 j > i，说明 nums[j - 1] 没有选，所有等于 nums[j - 1] 的数都不选
            if (j > i && nums[j] == nums[j - 1]) {
                continue;
            }
            path.add(nums[j]);
            dfs1(j + 1, target - nums[j], path, nums, res);
            path.remove(path.size() - 1);
        }
    }

    private void dfs2(int i, int target, List<Integer> path, int[] nums, List<List<Integer>> res) {
        // 枚举下一个要选的数
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        int n = nums.length;
        if (i == n || target < nums[i]) {
            return;
        }
        // 选
        path.add(nums[i]);
        dfs2(i + 1, target - nums[i], path, nums, res);
        path.remove(path.size() - 1);
        // 不选
        int x = nums[i];
        while (i < n && nums[i] == x) {
            i++;
        }
        dfs2(i, target, path, nums, res);
    }
}
