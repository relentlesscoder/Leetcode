package org.wshuai.leetcode;

/**
 * Created by Wei on 08/03/2017.
 * #0335 https://leetcode.com/problems/self-crossing/
 */
public class SelfCrossing {
	// time O(n)
	// draw all the possible scenarios of all crosses
	public boolean isSelfCrossing(int[] x) {
		int n = x.length;
		if(n <= 3){
			return false;
		}
		for(int i = 3; i < n; i++){
			// 4th line cross the 1st line
			if(x[i] >= x[i - 2]
					&& x[i - 1] <= x[i - 3]){
				return true;
			}
			// 5th line meets first line
			if(i >= 4 && x[i - 1] == x[i - 3]
					&& x[i] + x[i - 4] >= x[i - 2]){
				return true;
			}
			// 6th line cross first line
			if(i >= 5 && x[i - 2] >= x[i - 4]
					&& x[i - 1] <= x[i - 3]
					&& x[i - 1] + x[i - 5] >= x[i - 3]
					&& x[i] + x[i - 4] >= x[i - 2]){
				return true;
			}
		}
		return false;
	}
}
