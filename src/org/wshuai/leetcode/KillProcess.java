package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 9/30/2019.
 * #582 https://leetcode.com/problems/kill-process/
 */
public class KillProcess {
	public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
		List<Integer> res = new ArrayList<>();
		Map<Integer, List<Integer>> map = new HashMap<>();
		for(int i = 0; i < pid.size(); i++){
			int p = pid.get(i);
			int pp = ppid.get(i);
			if(!map.containsKey(pp)){
				map.put(pp, new ArrayList<Integer>());
			}
			map.get(pp).add(p);
		}
		LinkedList<Integer> queue = new LinkedList<>();
		queue.offerLast(kill);
		while(!queue.isEmpty()){
			int curr = queue.pollFirst();
			res.add(curr);
			if(!map.containsKey(curr)){
				continue;
			}
			for(int v: map.get(curr)){
				queue.offerLast(v);
			}
		}
		return res;
	}
}
