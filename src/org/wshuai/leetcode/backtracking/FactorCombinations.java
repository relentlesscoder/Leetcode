package org.wshuai.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/01/2016.
 * #0254 https://leetcode.com/problems/factor-combinations/
 */
public class FactorCombinations {

	// time O(sqrt(n) * log(n)), space O(log(n))
	public List<List<Integer>> getFactors(int n) {
		List<List<Integer>> res = new ArrayList<>();
		dfs(n, 2, new ArrayList<>(), res);
		return res;
	}

	private void dfs(int n, int start, List<Integer> nums, List<List<Integer>> res) {
		if (n == 1) {
			if (nums.size() > 1) {
				res.add(new ArrayList<>(nums));
			}
			return;
		}
		// 遍历所有因子
		for (int i = start; i * i <= n; i++) {
			if (n % i != 0) {
				continue;
			}
			nums.add(i);
			dfs(n / i, i, nums, res); // 注意这里传入 i 作为下一步的起始值避免重复
			nums.remove(nums.size() - 1);
		}
		// 当遍历所有所有因子后，最后一个数有可能不为 1 。 则我们需要
		// 强制取 1 (除以他自己) 让他满足最后的结束条件。
		nums.add(n);
		dfs(1, n, nums, res);
		nums.remove(nums.size() - 1);
	}

	private void dfs1(int n, int start, List<Integer> nums, List<List<Integer>> res) {
		// 更好理解但是慢很多的版本
		if (n == 1) {
			if (nums.size() > 1) {
				res.add(new ArrayList<>(nums));
			}
			return;
		}
		for (int i = start; i <= n; i++) {
			if (n % i != 0) {
				continue;
			}
			nums.add(i);
			dfs(n / i, i, nums, res);
			nums.remove(nums.size() - 1);
		}
	}
}
