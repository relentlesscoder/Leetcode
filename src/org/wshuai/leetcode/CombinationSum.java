package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 10/28/2016.
 * #0039 https://leetcode.com/problems/combination-sum/
 */
public class CombinationSum {

    // time O(k * 2^n), space O(n)
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        dfs(0, target, candidates, res, new ArrayList<>());
        return res;
    }

    private void dfs(int i, int target, int[] candidates, List<List<Integer>> res, List<Integer> path) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (i == candidates.length || target < candidates[i]) {
            return;
        }
        // 不选
        dfs(i + 1, target, candidates, res, path);
        // 选
        path.add(candidates[i]);
        // 注意因为允许重复所以还是从 i 开始
        dfs(i, target - candidates[i], candidates, res, path);
        path.remove(path.size() - 1);
    }
}
