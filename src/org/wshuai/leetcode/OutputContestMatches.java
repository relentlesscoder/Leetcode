package org.wshuai.leetcode;

/**
 * Created by Wei on 04/08/2017.
 * #0544 https://leetcode.com/problems/output-contest-matches/
 */
public class OutputContestMatches {
	// time O(log(n)), space O(n)
	public String findContestMatch(int n) {
		String[] res = new String[n];
		for(int i = 0; i < n; i++){
			res[i] = String.valueOf(i + 1);
		}
		while(n > 1){
			for(int i = 0; i < n / 2; i++){
				res[i] = "(" + res[i] + "," + res[n - 1 - i] + ")";
			}
			n /= 2;
		}
		return res[0];
	}
}
