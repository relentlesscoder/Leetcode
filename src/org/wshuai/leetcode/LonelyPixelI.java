package org.wshuai.leetcode;

/**
 * Created by Wei on 09/27/2019.
 * #0531 https://leetcode.com/problems/lonely-pixel-i/
 */
public class LonelyPixelI {
	// time O(m*n), space O(m+n)
	public int findLonelyPixel(char[][] picture) {
		int res = 0, m = picture.length, n = picture[0].length;
		int[] rows = new int[m], cols = new int[n];
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(picture[i][j] == 'B'){
					rows[i]++;
					cols[j]++;
				}
			}
		}
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(picture[i][j] == 'B' && rows[i] == 1 && cols[j] == 1){
					res++;
				}
			}
		}
		return res;
	}
}
