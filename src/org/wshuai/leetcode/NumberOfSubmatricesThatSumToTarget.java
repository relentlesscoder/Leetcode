package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/19/19.
 * #1074 https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/
 */
public class NumberOfSubmatricesThatSumToTarget {
	// https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/discuss/303750/JavaC%2B%2BPython-Find-the-Subarray-with-Target-Sum
	public int numSubmatrixSumTarget(int[][] matrix, int target) {
		int M = matrix.length;
		int N = matrix[0].length;
		// calculate the prefix sum for each row
		for(int i = 0; i < M; i++){
			for(int j = 1; j < N; j++){
				matrix[i][j] += matrix[i][j - 1];
			}
		}
		int res = 0;
		// for any columns between i, j (all submatriices formed by column i and j)
		for(int i = 0; i < N; i++){
			for(int j = i; j < N; j++){
				Map<Integer, Integer> counter = new HashMap<>();
				counter.put(0, 1);
				int cur = 0;
				// for each row
				for (int k = 0; k < M; k++) {
					// get the current
					cur += matrix[k][j] - (i > 0 ? matrix[k][i - 1] : 0);
					res += counter.getOrDefault(cur - target, 0);
					counter.put(cur, counter.getOrDefault(cur, 0) + 1);
				}
			}
		}
		return res;
	}
}
