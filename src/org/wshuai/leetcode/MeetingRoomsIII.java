package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 10/24/2023.
 * #2402 https://leetcode.com/problems/meeting-rooms-iii/
 */
public class MeetingRoomsIII {

	// time O(m * n * log(n)), space O(n)
	public int mostBooked(int n, int[][] meetings) {
		int roomHostsMostMeetings = 0;
		int[] meetingCounter = new int[n];
		PriorityQueue<int[]> meetingsInProgress = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
		PriorityQueue<Integer> availableRooms = new PriorityQueue<>();
		Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
		for (int i = 0; i < n; i++) {
			availableRooms.offer(i);
		}
		for (int i = 0; i < meetings.length; i++) {
			int start = meetings[i][0];
			while (!meetingsInProgress.isEmpty() && meetingsInProgress.peek()[0] <= start) {
				availableRooms.offer(meetingsInProgress.poll()[1]);
			}
			if (availableRooms.isEmpty()) {
				int[] earliestFinished = meetingsInProgress.poll();
				start = earliestFinished[0];
				availableRooms.offer(earliestFinished[1]);
			}
			int room = availableRooms.poll();
			meetingCounter[room]++;
			if (meetingCounter[room] > meetingCounter[roomHostsMostMeetings] || (meetingCounter[room] == meetingCounter[roomHostsMostMeetings] && room < roomHostsMostMeetings)) {
				roomHostsMostMeetings = room;
			}
			meetingsInProgress.offer(new int[] {start + meetings[i][1] - meetings[i][0], room});
		}
		return roomHostsMostMeetings;
	}
}
