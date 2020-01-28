package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

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
			if(!pq.isEmpty() && itr[0] >= pq.peek()){
				pq.poll();
			}
			pq.offer(itr[1]);
		}
		return pq.size();
	}
}
