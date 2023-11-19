package org.wshuai.leetcode;

/**
 * Created by Wei on 09/29/2023.
 * #2682 https://leetcode.com/problems/find-the-losers-of-the-circular-game/
 */
public class FindTheLosersOfTheCircularGame {

	// time O(?), space O(n)
	public int[] circularGameLosers(int n, int k) {
		int[] friends = new int[n];
		int curr = 0, round = 1, winners = 1;
		friends[0]++;
		while (true) {
			curr = (curr + round * k) % n;
			if (friends[curr] == 0) {
				winners++;
			}
			if (++friends[curr] == 2) {
				break;
			}
			round++;
		}
		int[] res = new int[n - winners];
		for (int i = 0, j = 0; i < n; i++) {
			if (friends[i] == 0) {
				res[j++] = i + 1;
			}
		}
		return res;
	}
}
