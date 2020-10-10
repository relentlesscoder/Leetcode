package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/05/2019.
 * #1152 https://leetcode.com/problems/analyze-user-website-visit-pattern/
 */
public class AnalyzeUserWebsiteVisitPattern {

	// time O(n^3), space O(n^3)
	public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
		List<String> res = new ArrayList<>();
		int n = username.length;
		Map<String, List<Log>> visited = new HashMap<>();
		for(int i = 0; i < n; i++){
			visited.putIfAbsent(username[i], new ArrayList<>());
			visited.get(username[i]).add(new Log(website[i], timestamp[i]));
		}
		String mostVisited = "";
		Map<String, Integer> count = new HashMap<>();
		for(String user : visited.keySet()){
			Set<String> seen = new HashSet<>();
			List<Log> logs = visited.get(user);
			Collections.sort(logs, (a, b) -> a.timestamp - b.timestamp);
			for(int i = 0; i < logs.size(); i++){
				for(int j = i + 1; j < logs.size(); j++){
					for(int k = j + 1; k < logs.size(); k++){
						String key = logs.get(i).website + "," + logs.get(j).website + "," + logs.get(k).website;
						if(!seen.contains(key)){
							count.put(key, count.getOrDefault(key, 0) + 1);
							seen.add(key);
						}
						if(mostVisited.length() == 0 || count.get(key) > count.get(mostVisited) || (count.get(key) == count.get(mostVisited) && key.compareTo(mostVisited) < 0)){
							mostVisited = key;
						}
					}
				}
			}
		}
		String[] domains = mostVisited.split(",");
		for(String d : domains){
			res.add(d);
		}
		return res;
	}

	private class Log{

		String website;
		int timestamp;

		public Log(String website, int timestamp){
			this.website = website;
			this.timestamp = timestamp;
		}
	}
}
