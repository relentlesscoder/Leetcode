package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 12/31/2019.
 * #0630 https://leetcode.com/problems/course-schedule-iii/
 */
public class CourseScheduleIII {
	// time O(n*log(n)), space O(n)
	public int scheduleCourse(int[][] courses) {
		Arrays.sort(courses, (a, b) -> a[1] - b[1]);
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
		int time = 0;
		for(int[] c : courses){
			time += c[0];
			pq.add(c[0]);
			// remove the courses take the longest time to complete
			while(time > c[1]){
				time -= pq.poll();
			}
		}
		return pq.size();
	}
}
