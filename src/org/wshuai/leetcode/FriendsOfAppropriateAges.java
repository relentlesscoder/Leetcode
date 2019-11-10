package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/10/2019.
 * #825 https://leetcode.com/problems/friends-of-appropriate-ages/
 */
public class FriendsOfAppropriateAges {
	public int numFriendRequests(int[] ages) {
		int res = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for(int a : ages){
			map.put(a, map.getOrDefault(a, 0) + 1);
		}
		for(int a : map.keySet()){
			for(int b : map.keySet()){
				res += request(a, b) ? map.get(a) * (map.get(b) - (a == b ? 1 : 0)) : 0;
			}
		}
		return res;
	}

	private boolean request(int a, int b){
		return !(b <= a / 2 + 7 || b > a || (b > 100 && a < 100));
	}
}
