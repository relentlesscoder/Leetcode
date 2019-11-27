package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 11/27/19.
 * #1125 https://leetcode.com/problems/smallest-sufficient-team/
 */
public class SmallestSufficientTeam {
	// 18ms
	public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
		int N = people.size();
		int[] skill_sets = new int[N];
		for(int i = 0; i < N; i++){
			int val = 0;
			for(String s : people.get(i)){
				int j = 0;
				for(; j < req_skills.length; j++){
					if(req_skills[j].equals(s)){
						break;
					}
				}
				val |= (1 << j);
			}
			skill_sets[i] = val;
		}
		int T = (1 << req_skills.length) - 1;
		int[] dp = new int[T + 1];
		int[][] path = new int[T + 1][2];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for(int i = 0; i < N; i++){
			int k = skill_sets[i];
			for(int j = T; j >= 0; j--){
				if(dp[j] != Integer.MAX_VALUE && dp[j] + 1 < dp[j | k]){
					dp[j | k] = dp[j] + 1;
					path[j | k] = new int[]{j, i};
				}
			}
		}
		int t = T;
		List<Integer> list = new ArrayList<Integer>();
		while(t > 0){
			list.add(path[t][1]);
			t = path[t][0];
		}
		int[] res = new int[list.size()];
		int i = 0;
		for(int j = list.size() - 1; j >=0; j--){
			res[i++] = list.get(j);
		}
		return res;
	}

	//48ms
	public int[] smallestSufficientTeam2D(String[] req_skills, List<List<String>> people) {
		int N = people.size();
		int[] skill_sets = new int[N];
		for(int i = 0; i < N; i++){
			int val = 0;
			for(String s : people.get(i)){
				int j = 0;
				for(; j < req_skills.length; j++){
					if(req_skills[j].equals(s)){
						break;
					}
				}
				val |= (1 << j);
			}
			skill_sets[i] = val;
		}
		int T = (1 << req_skills.length) - 1;
		int[][] dp = new int[N + 1][T + 1];
		int[][] path = new int[T + 1][2];
		Arrays.fill(dp[0], Integer.MAX_VALUE);
		dp[0][0] = 0;
		for(int i = 1; i <= N; i++){
			int k = skill_sets[i - 1];
			Arrays.fill(dp[i], Integer.MAX_VALUE);
			dp[i][0] = 0;
			for(int j = T; j >= 0; j--){
				dp[i][j] = dp[i - 1][j];
				if(dp[i - 1][j] != Integer.MAX_VALUE
					&& dp[i - 1][j] + 1 < dp[i][j | k]){
					dp[i][j | k] = dp[i - 1][j] + 1;
					path[j | k] = new int[]{j, i - 1};
				}
			}
		}
		int t = T;
		List<Integer> list = new ArrayList<Integer>();
		while(t > 0){
			list.add(path[t][1]);
			t = path[t][0];
		}
		int[] res = new int[list.size()];
		int i = 0;
		for(int j = list.size() - 1; j >=0; j--){
			res[i++] = list.get(j);
		}
		return res;
	}
}
