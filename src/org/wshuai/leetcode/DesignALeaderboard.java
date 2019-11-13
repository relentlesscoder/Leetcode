package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/13/19.
 * #1244 https://leetcode.com/problems/design-a-leaderboard/
 */
public class DesignALeaderboard {
	Map<Integer, Integer> p;
	TreeMap<Integer, Set<Integer>> s;

	public DesignALeaderboard() {
		p = new HashMap<>();
		// in the lambda, the type is the type of the key
		s = new TreeMap<>((a, b) -> b - a);
	}

	public void addScore(int playerId, int score) {
		if(p.containsKey(playerId)){
			int prev = p.get(playerId);
			s.get(prev).remove(playerId);
			score += prev;
			p.put(playerId, score);
			s.putIfAbsent(score, new HashSet<>());
			s.get(score).add(playerId);
		}else{
			p.put(playerId, score);
			s.putIfAbsent(score, new HashSet<>());
			s.get(score).add(playerId);
		}
	}

	public int top(int K) {
		int sum = 0;
		for(Map.Entry<Integer, Set<Integer>> entry : s.entrySet()){
			int cnt = Math.min(K, entry.getValue().size());
			sum += entry.getKey() * cnt;
			K -= cnt;
			if(K == 0){
				break;
			}
		}
		return sum;
	}

	public void reset(int playerId) {
		int prev = p.get(playerId);
		p.put(playerId, 0);
		s.get(prev).remove(playerId);
		s.putIfAbsent(0, new HashSet<>());
		s.get(0).add(playerId);
	}
}

/**
 * Your Leaderboard object will be instantiated and called as such:
 * Leaderboard obj = new Leaderboard();
 * obj.addScore(playerId,score);
 * int param_2 = obj.top(K);
 * obj.reset(playerId);
 */
