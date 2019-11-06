package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/5/2019.
 * #1152 https://leetcode.com/problems/analyze-user-website-visit-pattern/
 */
public class AnalyzeUserWebsiteVisitPattern {
	public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
		int N = username.length;
		Integer[] order = new Integer[N];
		for(int i = 0; i < N; i++){
			order[i] = i;
		}
		Arrays.sort(order, (a, b) -> timestamp[a] - timestamp[b]);
		Map<String, List<String>> map = new HashMap<>();
		for(int i = 0; i < order.length; i++){
			map.putIfAbsent(username[order[i]], new ArrayList<>());
			map.get(username[order[i]]).add(website[order[i]]);
		}
		Map<List<String>, Integer> count = new HashMap<>();
		for(List<String> lst : map.values()){
			if(lst.size() < 3){
				continue;
			}
			Set<String> used = new HashSet<String>();
			for(int i = 0; i < lst.size() - 2; i++){
				for(int j = i + 1; j < lst.size() - 1; j++){
					for(int k = j + 1; k < lst.size(); k++){
						List<String> key = Arrays.asList(lst.get(i), lst.get(j), lst.get(k));
						String v = String.join(",", key);
						// same visit pattern is only added once for each user
						if(!used.contains(v)){
							count.put(key, count.getOrDefault(key, 0) + 1);
							used.add(v);
						}
					}
				}
			}
		}
		PriorityQueue<Map.Entry<List<String>, Integer>> pq =
			new PriorityQueue<>((a, b) -> a.getValue() == b.getValue() ?
				String.join(",", a.getKey()).compareTo(String.join(",", b.getKey()))
				: b.getValue() - a.getValue());
		for(Map.Entry<List<String>, Integer> entry : count.entrySet()){
			pq.offer(entry);
		}
		return pq.peek().getKey();
	}
}
