package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Wei on 01/01/2020.
 * #0710 https://leetcode.com/problems/random-pick-with-blacklist/
 */
public class RandomPickWithBlacklist {

	private int M;
	private Random rand = new Random();
	private Map<Integer, Integer> map = new HashMap<>();

	// time O(n)
	// https://leetcode.com/problems/random-pick-with-blacklist/discuss/144624/Java-O(B)-O(1)-HashMap
	public RandomPickWithBlacklist(int N, int[] blacklist) {

		// M is the number of valid number in [0, N) that
		// are not on blacklist
		M = N - blacklist.length;
		for(int b : blacklist){
			map.put(b, -1);
		}
		// if a number less than M is on the black list,
		// find a valid number from range [M, N) to map it
		for(int b : blacklist){
			if(b < M){
				while(map.containsKey(N - 1)){
					N--;
				}
				map.put(b, N - 1);
				N--;
			}
		}
	}

	public int pick() {
		int r = rand.nextInt(M);
		return map.getOrDefault(r, r);
	}
}
