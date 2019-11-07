package org.wshuai.leetcode;

/**
 * Created by Wei on 11/7/19.
 * #1208 https://leetcode.com/problems/get-equal-substrings-within-budget/
 */
public class GetEqualSubstringsWithinBudget {
	public int equalSubstring(String s, String t, int maxCost) {
		int res = 0;
		int N = s.length();
		int[] diff = new int[N];
		for(int i = 0; i < N; i++){
			int d = s.charAt(i) - t.charAt(i);
			diff[i] = Math.abs(d);
		}
		int i = 0;
		int j = 0;
		int curr = 0;
		while(j < N && curr <= maxCost){
			curr += diff[j];
			if(curr > maxCost){
				res = Math.max(j - i, res);
				while(curr > maxCost){
					curr -= diff[i++];
				}
			}
			j++;
		}
		return Math.max(j - i, res);
	}
}
