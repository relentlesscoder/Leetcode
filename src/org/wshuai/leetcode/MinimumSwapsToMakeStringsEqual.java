package org.wshuai.leetcode;

/**
 * Created by Wei on 11/13/19.
 * #1247 https://leetcode.com/problems/minimum-swaps-to-make-strings-equal/
 */
public class MinimumSwapsToMakeStringsEqual {
	public int minimumSwap(String s1, String s2) {
		int x1 = 0;
		int y1 = 0;
		int x2 = 0;
		int y2 = 0;

		for(int i = 0; i < s1.length(); i++){
			char c1 = s1.charAt(i);
			char c2 = s2.charAt(i);
			if(c1 == c2){
				continue;
			}
			if(c1 == 'x'){
				x1++;
			}else{
				y1++;
			}
			if(c2 == 'x'){
				x2++;
			}else{
				y2++;
			}
		}

		// easy to see it is impossible that if total number of x or y is odd
		if((x1 + x2) % 2 == 1 || (y1 + y2) % 2 == 1){
			return -1;
		}

		// from the given example
		// pairs like xx -> yy, yy -> xx requires 1 swap each
		// other pairs like xy -> yx requires 2 swaps each
		return x1 / 2 + y1 / 2 + (x1 % 2) * 2;
	}
}
