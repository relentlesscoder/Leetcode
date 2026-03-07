package org.wshuai.leetcode.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 11/20/2016.
 * #0253 https://leetcode.com/problems/meeting-rooms-ii/
 */
public class MeetingRoomsII {

    // time O(n * log(n)), space O(n)
    public int minMeetingRooms(int[][] intervals) {
		// 同 #2406
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            if (!minQueue.isEmpty() && minQueue.peek() <= intervals[i][0]) {
                minQueue.poll();
            }
            minQueue.offer(intervals[i][1]);
        }
        return minQueue.size();
    }

    // time O(n + MAX), space O(MAX)
    public int minMeetingRoomsDiffArray(int[][] intervals) {
        int res = 0, rooms = 0, max = -1;
        for (int[] in : intervals) {
            max = Math.max(max, in[1]);
        }
        int[] diff = new int[max + 1];
        for (int[] in : intervals) {
            diff[in[0]]++;
            diff[in[1]]--;
        }
        for (int i = 0; i <= max; i++) {
            rooms += diff[i];
            res = Math.max(res, rooms);
        }
        return res;
    }
}
