package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by Wei on 11/20/16.
 * #253 https://leetcode.com/problems/meeting-rooms-ii/
 */
public class MeetingRoomsII {
	//O(n*lg(n))
	public int minMeetingRooms(Interval[] intervals) {
		if (intervals == null || intervals.length == 0) {
			return 0;
		}
		int len = intervals.length;
		Arrays.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval x, Interval y) {
				return x.start - y.start;
			}
		});
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.offer(intervals[0].end);
		for (int i = 1; i < len; i++) {
			if (intervals[i].start >= pq.peek()) {
				pq.poll();
			}
			pq.offer(intervals[i].end);
		}
		return pq.size();
	}
}
