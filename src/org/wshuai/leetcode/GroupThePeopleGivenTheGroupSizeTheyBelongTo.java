package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 12/9/2019.
 * #1282 https://leetcode.com/problems/group-the-people-given-the-group-size-they-belong-to/
 */
public class GroupThePeopleGivenTheGroupSizeTheyBelongTo {
	public List<List<Integer>> groupThePeople(int[] groupSizes) {
		List<List<Integer>> res = new ArrayList<>();
		Map<Integer, List<Integer>> map = new HashMap<>();
		for(int i = 0; i < groupSizes.length; i++){
			int cnt = groupSizes[i];
			if(cnt == 1){
				res.add(Arrays.asList(i));
				continue;
			}
			if(!map.containsKey(cnt)){
				map.put(cnt, new ArrayList<>());
			}
			map.get(cnt).add(i);
			if(map.get(cnt).size() == cnt){
				res.add(map.get(cnt));
				map.put(cnt, new ArrayList<>());
			}
		}
		return res;
	}
}
