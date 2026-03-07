package org.wshuai.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 10/17/2016.
 * #0047 https://leetcode.com/problems/permutations-ii/
 */
public class PermutationsII {

    // time O(n * n!), space O(n)
    public List<List<Integer>> permuteUnique(int[] nums) {
        // #0491 去重
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        dfs(new ArrayList<>(), 0, nums, res);
        // dfs1(new ArrayList<>(), 0, nums, res);
        return res;
    }

    private void dfs(List<Integer> path, int used, int[] nums, List<List<Integer>> res) {
        // 二进制掩码 used 表示数组中目前被使用过的索引
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 二进制掩码 visited 表示递归树中同一层已经被使用过的元素值 (去重)
        int visited = 0;
        for (int j = 0; j < nums.length; j++) {
            if (((1 << j) & used) > 0 || ((1 << (nums[j] + 10)) & visited) > 0) {
                continue;
            }
            visited |= (1 << (nums[j] + 10));
            used |= (1 << j);
            path.add(nums[j]);
            dfs(path, used, nums, res);
            // 恢复现场，注意 used 要恢复但是 visited 不用
            path.remove(path.size() - 1);
            used ^= (1 << j);
        }
    }

    private void dfs1(List<Integer> path, int used, int[] nums, List<List<Integer>> res) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 因为数组已经排序过了所以也可以不用哈希集，如果前面一个元素值与当前值一样且未被使用过则当前值
        // 也不应该被使用。这里未被使用的意思实际上是指前一个相同元素的递归已经结束所以不应该再重复处理
        // 相同的元素。如果数组不是排序过的则必须使用哈希集 (#0491) 。
        for (int j = 0; j < nums.length; j++) {
            if (j > 0 && nums[j] == nums[j - 1] && (((1 << (j - 1)) & used) == 0)) {
                continue;
            }
            if (((1 << j) & used) > 0) {
                continue;
            }
            used |= (1 << j);
            path.add(nums[j]);
            dfs1(path, used, nums, res);
            path.remove(path.size() - 1);
            used ^= (1 << j);
        }
    }
}
