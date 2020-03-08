package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 02/12/2020.
 * #1349 https://leetcode.com/problems/maximum-students-taking-exam/
 */
public class MaximumStudentsTakingExam {
	public int maxStudents(char[][] seats) {
		int m = seats.length, n = seats[0].length, res = 0;
		Map<Integer, Integer> prev = new HashMap<>();
		prev.put(0, 0);
		for(int i = 0; i < m; i++){
			Map<Integer, Integer> cur = new HashMap<>();
			for(int key : prev.keySet()){
				dfs(i, 0, 0, 0, seats, cur, key, prev.get(key), m, n);
			}
			prev = cur;
		}
		for(int max : prev.values()){
			res = Math.max(res, max);
		}
		return res;
	}

	private void dfs(int row, int start, int status, int count, char[][] seats, Map<Integer, Integer> cur, int prevStatus, int prevMax, int m, int n){
		if(start == n){
			cur.put(status, Math.max(cur.getOrDefault(status, 0), count + prevMax));
			return;
		}
		dfs(row, start + 1, status, count, seats, cur, prevStatus, prevMax, m, n);
		if(seats[row][start] == '.' && canSit(row, start, seats, status, prevStatus, n)){
			dfs(row, start + 1, status | (1 << start), count + 1, seats, cur, prevStatus, prevMax, m, n);
		}
	}

	private boolean canSit(int i, int j, char[][] seats, int status, int prevStatus, int n){
		if(j > 0 && (((status >> (j - 1)) & 1) == 1 || ((prevStatus >> (j - 1)) & 1) == 1)){
			return false;
		}
		if(j < n - 1 && ((prevStatus >> (j + 1)) & 1) == 1){
			return false;
		}
		return true;
	}
}
