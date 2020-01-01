package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Wei on 1/1/2020.
 * #710 https://leetcode.com/problems/random-pick-with-blacklist/
 */
public class RandomPickWithBlacklist {
	private int M;
	private Random r;
	private Map<Integer, Integer> map;

	// https://leetcode.com/problems/random-pick-with-blacklist/discuss/144624/Java-O(B)-O(1)-HashMap
	public RandomPickWithBlacklist(int N, int[] blacklist) {
		map = new HashMap<>();
		for(int b : blacklist){
			map.put(b, -1);
		}
		M = N - map.size();
		for(int b : blacklist){
			if(b < M){
				while(map.containsKey(N - 1)){
					N--;
				}
				map.put(b, N - 1);
				N--;
			}
		}
		r = new Random();
	}

	public int pick() {
		int p = r.nextInt(M);
		if(map.containsKey(p)){
			return map.get(p);
		}
		return p;
	}
}
