package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 9/17/19.
 * #465 https://leetcode.com/problems/optimal-account-balancing/
 */
public class OptimalAccountBalancing {
	public int minTransfers(int[][] transactions) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int[] tran : transactions) {
			map.put(tran[0], map.getOrDefault(tran[0], 0) - tran[2]);
			map.put(tran[1], map.getOrDefault(tran[1], 0) + tran[2]);
		}
		List<Integer> debt = new ArrayList<>();
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() != 0) {
				debt.add(entry.getValue());
			}
		}
		return dfs(debt, 0);
	}

	private int dfs(List<Integer> debt, int s) {
		while (s < debt.size() && debt.get(s) == 0) {
			s++;
		}
		int res = Integer.MAX_VALUE;
		for (int i = s + 1, prev = 0; i < debt.size(); i++) {
			if (debt.get(i) != prev && debt.get(i) * debt.get(s) < 0) {
				debt.set(i, debt.get(i) + debt.get(s));
				res = Math.min(res, 1 + dfs(debt, s + 1));
				prev = debt.get(i) - debt.get(s);
				debt.set(i, debt.get(i) - debt.get(s));
			}
		}
		return res < Integer.MAX_VALUE ? res : 0;
	}
}
