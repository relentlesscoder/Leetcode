package org.wshuai.leetcode;

/**
 * Created by Wei on 12/14/2020.
 * #1688 https://leetcode.com/problems/count-of-matches-in-tournament/
 */
public class CountOfMatchesInTournament {

	// time O(log(n))
	public int numberOfMatches(int n) {
		if(n == 1){
			return 0;
		}
		int res = 0;
		while(true){
			int r = n % 2;
			n = ((n - r) >> 1);
			res += n;
			if(n == 1 && r == 0){
				return res;
			}
			n += r;
		}
	}
}
