package org.wshuai.leetcode;

/**
 * Created by Wei on 03/29/2020.
 * #1395 https://leetcode.com/problems/count-number-of-teams/
 */
public class CountNumberOfTeams {
	// time O(n^2), space O(n)
	public int numTeams(int[] rating) {
		int res = 0, n = rating.length;
		if(n < 3){
			return 0;
		}
		int[] asc = new int[n], desc = new int[n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < i; j++){
				if(rating[i] > rating[j]){
					res += asc[j];
					asc[i]++;
				}
				if(rating[i] < rating[j]){
					res += desc[j];
					desc[i]++;
				}
			}
		}
		return res;
	}
}
