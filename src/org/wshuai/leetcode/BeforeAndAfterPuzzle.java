package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/8/2019.
 * #1181 https://leetcode.com/problems/before-and-after-puzzle/
 */
public class BeforeAndAfterPuzzle {
	public List<String> beforeAndAfterPuzzles(String[] phrases) {
		List<String> res = new ArrayList<>();
		TreeSet<String> set = new TreeSet<>();
		Map<String, List<Integer>> map = new HashMap<>();
		List<String> ends = new ArrayList<>();
		for(int i = 0; i < phrases.length; i++){
			String[] arr = phrases[i].split(" ");
			String start = arr[0];
			if(!map.containsKey(start)){
				map.put(start, new ArrayList<>());
			}
			map.get(start).add(i);
			ends.add(arr[arr.length - 1]);
		}
		for(int i = 0; i < phrases.length; i++){
			if(!map.containsKey(ends.get(i))){
				continue;
			}
			for(int j: map.get(ends.get(i))){
				if(i == j){
					continue;
				}
				String s = phrases[j];
				set.add(phrases[i] + (s.indexOf(" ") == -1 ? "" : s.substring(s.indexOf(" "))));
			}
		}
		for(String v: set){
			res.add(v);
		}
		return res;
	}
}
