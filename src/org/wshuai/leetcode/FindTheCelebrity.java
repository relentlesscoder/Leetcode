package org.wshuai.leetcode;

/**
 * Created by Wei on 10/01/2016.
 * #0277 https://leetcode.com/problems/find-the-celebrity/
 */
public class FindTheCelebrity {
	// time O(n)
	public int findCelebrity(int n) {
		int cand = 0;
		for(int i = 1; i < n; i++){
			if(knows(cand, i)){
				cand = i;
			}
		}
		for(int i = 0; i < n; i++){
			if(cand == i){
				continue;
			}
			if(!knows(i, cand) || knows(cand, i)){
				return -1;
			}
		}
		return cand;
	}

	// dummy method
	private boolean knows(int a, int b) {
		return false;
	}
}
