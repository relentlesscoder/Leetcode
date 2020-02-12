package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 02/12/2020.
 * #1348 https://leetcode.com/problems/tweet-counts-per-frequency/
 */
public class TweetCountsPerFrequency {
	private Map<String, TreeMap<Integer, Integer>> map;

	public TweetCountsPerFrequency() {
		map = new HashMap<>();
	}

	// time O(log(n))
	public void recordTweet(String tweetName, int time) {
		map.putIfAbsent(tweetName, new TreeMap<>());
		TreeMap<Integer, Integer> tm = map.get(tweetName);
		if(tm.containsKey(time)){
			tm.put(time, tm.get(time) + 1);
		}else{
			tm.put(time, 1);
		}
	}

	// time O(log(n)*k)
	public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
		TreeMap tm = map.get(tweetName);
		List<Integer> res = new ArrayList<>();
		if(tm == null){
			return res;
		}
		int interval = 60;
		if(freq.equals("hour")){
			interval = 3_600;
		}else if(freq.equals("day")){
			interval = 86_400;
		}
		for(int i = startTime; i <= endTime; i += interval){
			int j = Math.min(i + interval, endTime + 1), count = 0;
			SortedMap<Integer, Integer> submap = tm.subMap(i, j);
			for(int val : submap.values()){
				count += val;
			}
			res.add(count);
		}
		return res;
	}
}
