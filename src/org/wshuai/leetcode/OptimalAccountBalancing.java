package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 09/17/2019.
 * #0465 https://leetcode.com/problems/optimal-account-balancing/
 */
public class OptimalAccountBalancing {
	// time O(n!)
	public int minTransfers(int[][] transactions) {
		// build debt array
		int[] debt = buildDebt(transactions);
		// offset debts with opposite equal amount
		Set<Integer> set = optimizeDebt(debt);
		int res = set.size() / 2;
		int[] target = new int[debt.length - set.size()];
		int index = 0;
		for (int j = 0; j < debt.length; j++) {
			if (!set.contains(j)) {
				target[index++] = debt[j];
			}
		}
		// use DFS to solve debts left
		return res + dfs(target, 0);
	}

	private int[] buildDebt(int[][] transactions) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int[] tran : transactions) {
			map.put(tran[0], map.getOrDefault(tran[0], 0) - tran[2]);
			map.put(tran[1], map.getOrDefault(tran[1], 0) + tran[2]);
		}
		int count = 0;
		List<Integer> temp = new ArrayList<>();
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() != 0) {
				count++;
				temp.add(entry.getValue());
			}
		}
		int[] debt = new int[count];
		int i = 0;
		for (int d : temp) {
			debt[i++] = d;
		}
		return debt;
	}

	private Set<Integer> optimizeDebt(int[] debt) {
		Arrays.sort(debt);
		Set<Integer> set = new HashSet<>();
		int left = 0, right = debt.length - 1;
		while (left < right) {
			if (debt[left] + debt[right] == 0) {
				set.add(left);
				set.add(right);
				left++;
				right--;
			} else if (debt[left] + debt[right] < 0) {
				left++;
			} else {
				right--;
			}
		}
		return set;
	}

	private int dfs(int[] debt, int cur) {
		while (cur < debt.length && debt[cur] == 0) {
			cur++;
		}
		if (cur == debt.length) {
			return 0;
		}
		int res = Integer.MAX_VALUE;
		for (int i = cur + 1; i < debt.length; i++) {
			if (debt[i] * debt[cur] < 0) {
				debt[i] += debt[cur];
				res = Math.min(res, 1 + dfs(debt, cur + 1));
				debt[i] -= debt[cur];
			}
		}
		return res;
	}
}
