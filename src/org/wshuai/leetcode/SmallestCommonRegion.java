package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/22/19.
 * #1257 https://leetcode.com/problems/smallest-common-region/
 */
public class SmallestCommonRegion {
	public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
		Map<String, String> map = new HashMap<>();
		for(List<String> r : regions){
			for(int i = 1; i < r.size(); i++){
				map.put(r.get(i), r.get(0));
			}
		}
		Set<String> path = new HashSet<>();
		String val = region1;
		while(val != null){
			path.add(val);
			val = map.get(val);
		}
		val = region2;
		while(val != null){
			if(path.contains(val)){
				return val;
			}
			val = map.get(val);
		}
		return "";
	}
}
