package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 07/20/2017.
 * #0305 https://leetcode.com/problems/number-of-islands-ii/
 */
public class NumberOfIslandsII {
	private static final int[] dirs = new int[]{0, 1, 0, -1, 0};

	public List<Integer> numIslands2(int m, int n, int[][] positions) {
		List<Integer> res = new ArrayList<>();
		if (positions == null || positions.length == 0) {
			return res;
		}
		int lands = 0, count = 0;
		int[] root = new int[m * n];
		Arrays.fill(root, -1);
		for (int[] p : positions) {
			int i = p[0] * n + p[1];
			if (root[i] != -1) {
				res.add(count);
				continue;
			}
			root[i] = i;
			count++;
			for (int k = 0; k < 4; k++) {
				int x = p[0] + dirs[k], y = p[1] + dirs[k + 1];
				int j = x * n + y;
				if (x >= 0 && x < m && y >= 0 && y < n && root[j] != -1) {
					int r1 = findRoot(j, root);
					int r2 = findRoot(i, root);
					if (r1 != r2) {
						root[r2] = r1;
						count--;
					}
				}
			}
			res.add(count);
		}
		return res;
	}

	private int findRoot(int i, int[] root) {
		if (i != root[i]) {
			root[i] = findRoot(root[i], root);
		}
		return root[i];
	}
}
