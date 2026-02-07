package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/28/2016.
 * #0077 https://leetcode.com/problems/combinations/
 */
public class Combinations {

    // time O(k * C(k, n)), space O(k)
    public List<List<Integer>> combineReverse(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfsR1(n, k, new ArrayList<>(), res);
        // dfsR2(n, k, new ArrayList<>(), res);
        return res;
    }

    private void dfsR1(int i, int k, List<Integer> path, List<List<Integer>> res) {
        int d = k - path.size(); // 计算还需要选几个数
        if (d == 0) { // 已选到 k 个数
            res.add(new ArrayList<>(path));
            return;
        }
        // 当前这个数可以选的范围是 [i, d], 因为选完这个数我们必须至少留下 d - 1 个数
        for (int j = i; j >= d; j--) {
            path.add(j);
            dfsR1(j - 1, k, path, res);
            path.remove(path.size() - 1);
        }
    }

    private void dfsR2(int i, int k, List<Integer> path, List<List<Integer>> res) {
        // 选或者不选的写法
        int d = k - path.size();
        if (d == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (i > d) { // 如果当前元素不选后面还有足够多的元素则可以不选
            dfsR2(i - 1, k, path, res); // 不选
        }
        // 选
        path.add(i);
        dfsR2(i - 1, k, path, res);
        path.remove(path.size() - 1);
    }

    // time O(k * C(k, n)), space O(k)
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs1(1, n, k, new ArrayList<>(), res);
        return res;
    }

    private void dfs1(int i, int n, int k, List<Integer> path, List<List<Integer>> res) {
        int d = k - path.size(); // 计算还需要选几个数
        if (d == 0) { // 已选到 k 个数
            res.add(new ArrayList<>(path));
            return;
        }
        // 当前这个数可以选的范围是 [i, n - d + 1], 因为选完这个数我们必须至少留下 d - 1 个数
        for (int j = i; j <= n - d + 1; j++) {
            path.add(j);
            dfs1(j + 1, n, k, path, res);
            path.remove(path.size() - 1);
        }
    }
}
