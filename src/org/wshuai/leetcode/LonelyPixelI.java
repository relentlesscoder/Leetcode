package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 9/27/19.
 * #531 https://leetcode.com/problems/lonely-pixel-i/
 */
public class LonelyPixelI {
	public int findLonelyPixel(char[][] picture) {
		int r = picture.length;
		int c = picture[0].length;
		int[] rows = new int[r];
		int[] cols = new int[c];
		List<int[]> blacks = new ArrayList<>();
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				if(picture[i][j] == 'B'){
					blacks.add(new int[]{i, j});
					rows[i]++;
					cols[j]++;
				}
			}
		}
		int count = 0;
		for(int[] b: blacks){
			if(rows[b[0]] > 1 || cols[b[1]] > 1){
				continue;
			}
			count++;
		}
		return count;
	}
}
