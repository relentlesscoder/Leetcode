package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/19/2016.
 * #0252 https://leetcode.com/problems/meeting-rooms/
 */
public class MeetingRooms {
	// time O(n*log(n))
	public boolean canAttendMeetings(int[][] intervals) {
		if(intervals == null || intervals.length <= 1){
			return true;
		}
		int n = intervals.length;
		Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
		for(int i = 1; i < n; i++){
			if(intervals[i][0] < intervals[i - 1][1]){
				return false;
			}
		}
		return true;
	}
}
