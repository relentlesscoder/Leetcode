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

	// the moment you realize a call to knows(i,j) eliminates either i or j
	// the problem is solved. knows(i,j) == true then I can't be a celeb.
	// since a celeb knows nobody and knows(i,j) == false then j can't be
	// a celeb since everyone must know the celeb.
	// example [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
	// assume after first pass, i stopped at 7, and we checked knows for 0 -> 1,
	// 1 -> 3, 3 -> 4, 4 -> 7.
	// we know:
	// 		1. 0, 1, 3, 4 are excluded directly since they knows someone
	//      2. 2, 5, 6 are excluded since everyone else should know the celebrity
	//      3. 8, 9, 10 are also excluded since 7 does not know them
	// so 7 is the only possible candidate
}
