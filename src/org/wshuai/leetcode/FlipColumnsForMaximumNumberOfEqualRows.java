package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/18/2019.
 * #1072 https://leetcode.com/problems/flip-columns-for-maximum-number-of-equal-rows/
 */
public class FlipColumnsForMaximumNumberOfEqualRows {

	// same idea as https://leetcode.com/problems/flip-columns-for-maximum-number-of-equal-rows/discuss/303897/Java-easy-solution-%2B-explanation
	public int maxEqualRowsAfterFlips(int[][] matrix) {
		Map<String, Integer> map = new HashMap<>();
		int res = 0;
		int m = matrix.length;
		int n = matrix[0].length;
		for(int i = 0; i < m; i++){
			StringBuilder sb1 = new StringBuilder();
			StringBuilder sb2 = new StringBuilder();
			for(int j = 0; j < n; j++){
				sb1.append(matrix[i][j]);
				sb2.append(1 - matrix[i][j]);
			}
			String s1 = sb1.toString();
			String s2 = sb2.toString();
			map.put(s1, map.getOrDefault(s1, 0) + 1);
			map.put(s2, map.getOrDefault(s2, 0) + 1);
		}
		for(int cnt: map.values()){
			res = Math.max(res, cnt);
		}
		return res;
	}
}
