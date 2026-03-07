package org.wshuai.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/28/2016.
 * #0216 https://leetcode.com/problems/combination-sum-iii/
 */
public class CombinationSumIII {

    // time O(k * C(9, k)), space O(k)
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(9, n, k, new ArrayList<>(), res);
        // dfs1(9, n, k, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int i, int n, int k, List<Integer> path, List<List<Integer>> res) {
        int d = k - path.size(); // 计算还需要选几个数
        if (d == 0) { // 已选到 k 个数
            if (n == 0) { // k 个数的和正好为 n
                res.add(new ArrayList<>(path));
            }
            return;
        }
        if (n <= 0) { // 还没选到 k 个数和就大于等于 n 则提前结束
            return;
        }
        // 当前这个数可以选的范围是 [i, d], 因为选完这个数我们必须至少留下 d - 1 个数
        for (int j = i; j >= d; j--) {
            path.add(j);
            dfs(j - 1, n - j, k, path, res);
            path.remove(path.size() - 1);
        }
    }

    private void dfs1(int i, int n, int k, List<Integer> path, List<List<Integer>> res) {
        // 选或者不选的写法
        int d = k - path.size();
        if (d == 0) {
            if (n == 0) {
                res.add(new ArrayList<>(path));
            }
            return;
        }
        if (n <= 0) {
            return;
        }
        if (i > d) {
            dfs1(i - 1, n, k, path, res);
        }
        path.add(i);
        dfs1(i - 1, n - i, k, path, res);
        path.remove(path.size() - 1);
    }
}
