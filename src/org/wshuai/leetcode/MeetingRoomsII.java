package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Created by Wei on 11/20/2016.
 * #0253 https://leetcode.com/problems/meeting-rooms-ii/
 */
public class MeetingRoomsII {

	// time O(n*lg(n)), space O(n)
	public int minMeetingRooms(int[][] intervals) {
		int res = 0;
		if(intervals == null || intervals.length == 0){
			return res;
		}
		Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for(int[] itr : intervals){
			// if starting time of the current meeting is
			// greater than the earliest ending time of all
			// ongoing meetings, we can reuse its meeting
			// room. Otherwise, we need one more room for
			// the new meeting.
			if(!pq.isEmpty() && itr[0] >= pq.peek()){
				pq.poll();
			}
			pq.offer(itr[1]);
		}
		return pq.size();
	}

	// time O(n*log(n)), space O(n)
	public int minMeetingRoomsRunningSum(int[][] intervals) {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		for(int[] in : intervals){
			map.put(in[0], map.getOrDefault(in[0], 0) + 1);
			map.put(in[1], map.getOrDefault(in[1], 0) - 1);
		}
		int res = 0, cur = 0;
		for(int d : map.values()){
			cur += d;
			res = Math.max(res, cur);
		}
		return res;
	}
}
