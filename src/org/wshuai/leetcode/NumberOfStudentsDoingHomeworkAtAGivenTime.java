package org.wshuai.leetcode;

/**
 * Created by Wei on 05/17/2020.
 * #1450 https://leetcode.com/problems/number-of-students-doing-homework-at-a-given-time/
 */
public class NumberOfStudentsDoingHomeworkAtAGivenTime {

	// time O(n)
	public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
		int res = 0;
		for(int i = 0; i < startTime.length; i++){
			if(startTime[i] <= queryTime && endTime[i] >= queryTime){
				res++;
			}
		}
		return res;
	}
}
