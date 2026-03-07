package org.wshuai.leetcode.other;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Wei on 01/29/2017.
 * #0491 https://leetcode.com/problems/increasing-subsequences/
 */
public class IncreasingSubsequences {

    // time O(n * 2^n), space O(n)
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(0, new ArrayList<>(), nums, res);
        return res;
    }

    private void dfs(int i, List<Integer> path, int[] nums, List<List<Integer>> res) {
        if (path.size() >= 2) {
            res.add(new ArrayList<>(path));
        }
        // 因为数组不一定是排序过的所以必须使用哈希集来保证在递归树的同一层同样的元素只能用一次。
        Set<Integer> used = new HashSet<Integer>();
        for (int j = i; j < nums.length; j++) {
            if (!path.isEmpty() && nums[j] < path.get(path.size() - 1)) {
                continue;
            }
            if (used.contains(nums[j])) {
                continue;
            }
            used.add(nums[j]);
            path.add(nums[j]);
            dfs(j + 1, path, nums, res);
            path.remove(path.size() - 1);
        }
    }
}
