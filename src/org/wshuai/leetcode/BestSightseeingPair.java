package org.wshuai.leetcode;

/**
 * Created by Wei on 10/30/2019.
 * #1014 https://leetcode.com/problems/best-sightseeing-pair/
 */
public class BestSightseeingPair {
	public int maxScoreSightseeingPair(int[] A) {
		int res = 0;
		int curr = 0;
		for(int a: A){
			res = Math.max(res, curr + a);
			curr = Math.max(curr, a) - 1;
		}
		return res;
	}
}
