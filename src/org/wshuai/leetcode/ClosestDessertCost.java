package org.wshuai.leetcode;

/**
 * Created by Wei on 10/03/2023.
 * #1774 https://leetcode.com/problems/closest-dessert-cost/
 */
public class ClosestDessertCost {

	private int res;

	// time O(n * 3^m), space O(m)
	public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
		res = baseCosts[0];
		for (int base : baseCosts) {
			dfs(base, toppingCosts, 0, target);
		}
		return res;
	}

	private void dfs(int current, int[] toppingCosts, int index, int target) {
		if (Math.abs(target - current) < Math.abs(target - res) || (Math.abs(target - current) == Math.abs(target - res) && current < res)) {
			res = current;
		}
		if (index == toppingCosts.length || current >= target) {
			return;
		}
		dfs(current, toppingCosts, index + 1, target);
		dfs(current + toppingCosts[index], toppingCosts, index + 1, target);
		dfs(current + toppingCosts[index] * 2, toppingCosts, index + 1, target);
	}
}
