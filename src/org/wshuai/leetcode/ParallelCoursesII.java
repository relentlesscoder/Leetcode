package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/05/2020.
 * #1494 https://leetcode.com/problems/parallel-courses-ii/
 */
public class ParallelCoursesII {

	// time O(N^2), space O(N), N = (1 << n)
	public int minNumberOfSemesters(int n, int[][] dependencies, int k) {
		int[] prerequisites = new int[n];
		for(int[] d : dependencies){
			prerequisites[d[1] - 1] |= (1 << (d[0] - 1));
		}
		int[] dp = new int[1 << n];
		Arrays.fill(dp, n);
		dp[0] = 0;
		for(int i = 0; i < dp.length; i++){
			int canTake = 0;
			for(int j = 0; j < n; j++){
				if((i & prerequisites[j]) == prerequisites[j]){
					canTake |= (1 << j);
				}
			}
			canTake &= (~i);
			for(int subCourses = canTake; subCourses > 0; subCourses = (subCourses - 1) & canTake){
				if(Integer.bitCount(subCourses) <= k){
					dp[i | subCourses] = Math.min(dp[i | subCourses], dp[i] + 1);
				}
			}
		}
		return dp[dp.length - 1];
	}
}
