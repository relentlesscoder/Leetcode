package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/10/2019.
 * #0825 https://leetcode.com/problems/friends-of-appropriate-ages/
 */
public class FriendsOfAppropriateAges {

	// time O(d^2), space O(n)
	public int numFriendRequests(int[] ages) {
		int res = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for(int a : ages){
			map.put(a, map.getOrDefault(a, 0) + 1);
		}
		for(int a : map.keySet()){
			for(int b : map.keySet()){
				if(request(a, b)){
					res += map.get(a) * (map.get(b) - (a == b ?  1 : 0));
				}
			}
		}
		return res;
	}

	private boolean request(int a, int b){
		return !(b <= (a >> 1) + 7 || b > a);
	}
}
