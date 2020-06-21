package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 04/19/2020.
 * #1419 https://leetcode.com/problems/minimum-number-of-frogs-croaking/
 */
public class MinimumNumberOfFrogsCroaking {

	// time O(n), sapce O(n)
	public int minNumberOfFrogs(String croakOfFrogs) {
		int res = 0, cur = 0, last = 0;
		Map<Character, Integer> map = new HashMap<>();
		for(char c : croakOfFrogs.toCharArray()){
			if(c == 'c'){
				res = Math.max(res, ++cur);
				map.put('r', map.getOrDefault('r', 0) + 1);
			}else if(c == 'r'){
				int d = map.getOrDefault('r', 0) - 1;
				if(d < 0){
					return -1;
				}
				map.put('r', d);
				map.put('o', map.getOrDefault('o', 0) + 1);
			}else if(c == 'o'){
				int d = map.getOrDefault('o', 0) - 1;
				if(d < 0){
					return -1;
				}
				map.put('o', d);
				map.put('a', map.getOrDefault('a', 0) + 1);
			}else if(c == 'a'){
				int d = map.getOrDefault('a', 0) - 1;
				if(d < 0){
					return -1;
				}
				map.put('a', d);
				map.put('k', map.getOrDefault('k', 0) + 1);
			}else if(c == 'k'){
				int d = map.getOrDefault('k', 0) - 1;
				if(d < 0){
					return -1;
				}
				map.put('k', d);
				--cur;
			}
		}
		for(int v : map.values()){
			last += v;
		}
		return last == 0 ? res : -1;
	}
}
