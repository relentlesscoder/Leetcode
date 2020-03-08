package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Wei on 03/01/2020.
 * #1366 https://leetcode.com/problems/rank-teams-by-votes/
 */
public class RankTeamsByVotes {
	public String rankTeams(String[] votes) {
		int n = votes[0].length();
		int[][] score = new int[n][26];
		List<Character> res = new ArrayList<>();
		for(char c : votes[0].toCharArray()){
			res.add(c);
		}
		for(String v : votes){
			for(int i = 0; i < n; i++){
				score[i][v.charAt(i) - 'A']++;
			}
		}
		Collections.sort(res, (a, b) ->{
			for(int i = 0; i < n; i++){
				if(score[i][a - 'A'] != score[i][b - 'A']){
					return score[i][b - 'A'] - score[i][a - 'A'];
				}
			}
			return a - b;
		});
		return toString(res);
	}

	private String toString(List<Character> list){
		StringBuilder sb = new StringBuilder();
		for(char c : list){
			sb.append(c);
		}
		return sb.toString();
	}
}
