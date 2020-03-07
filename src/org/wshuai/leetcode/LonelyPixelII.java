package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/27/2019.
 * #0533 https://leetcode.com/problems/lonely-pixel-ii/
 */
public class LonelyPixelII {
	// time O(m*n), space O(m+n)
	public int findBlackPixel(char[][] picture, int N) {
		if(picture == null || picture.length == 0 || picture[0].length == 0){
			return 0;
		}
		int res = 0, m = picture.length, n = picture[0].length;
		Map<String, Integer> map = new HashMap<>();
		int[] colCount = new int[n];
		for(int i = 0; i < m; i++){
			StringBuilder sb = new StringBuilder();
			int rowCount = 0;
			for(int j = 0; j < n; j++){
				if(picture[i][j] == 'B'){
					rowCount++;
					colCount[j]++;
				}
				sb.append(picture[i][j]);
			}
			// For each row, add the rwo to map only if
			// row count of black pixels equals to N.
			// Therefore map contains all potential rows that contains
			// black pixels.
			if(rowCount == N){
				String key = sb.toString();
				map.put(key, map.getOrDefault(key, 0) + 1);
			}
		}
		for(String key : map.keySet()){
			// For each of the potential row, check only if the number
			// of same rows equals to N. If the count is not N, it
			// breaks the rule #1.
			if(map.get(key) == N){
				// For each of the column of the valid rows, check if the
				// column (with black pixel) has N black pixels since it's
				// possible that the column has more than N black pixels.
				for(int j = 0; j < n; j++){
					if(key.charAt(j) == 'B' && colCount[j] == N){
						res += N;
					}
				}
			}
		}
		return res;
	}
}
