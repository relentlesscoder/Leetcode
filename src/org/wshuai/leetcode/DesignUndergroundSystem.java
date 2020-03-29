package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 03/29/2020.
 * #1396 https://leetcode.com/problems/design-underground-system/
 */
public class DesignUndergroundSystem {

	private Map<String, Map<String, int[]>> statistics;
	private Map<Integer, CheckIn> checkIns;

	public DesignUndergroundSystem() {
		statistics = new HashMap<>();
		checkIns = new HashMap<>();
	}

	public void checkIn(int id, String stationName, int t) {
		checkIns.put(id, new CheckIn(stationName, t));
	}

	public void checkOut(int id, String stationName, int t) {
		CheckIn ci = checkIns.get(id);
		String from = ci.sid;
		int time = ci.time;
		statistics.putIfAbsent(from, new HashMap<>());
		int[] cur = statistics.get(from).getOrDefault(stationName, new int[]{0, 0});
		statistics.get(from).put(stationName, new int[]{cur[0] + t - time, cur[1] + 1});
	}

	public double getAverageTime(String startStation, String endStation) {
		int[] cur = statistics.get(startStation).getOrDefault(endStation, new int[]{0, 0});
		return (double)cur[0]/cur[1];
	}

	private class CheckIn{

		private String sid;

		private int time;

		public CheckIn(String sid, int time){
			this.time = time;
			this.sid = sid;
		}

	}
}
