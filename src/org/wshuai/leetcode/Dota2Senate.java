package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 11/12/19.
 * #649 https://leetcode.com/problems/dota2-senate/
 */
public class Dota2Senate {
	// greedy, always to ban the next senate in the counter party
	public String predictPartyVictory(String senate) {
		LinkedList<Integer> qr = new LinkedList<>();
		LinkedList<Integer> qd = new LinkedList<>();
		int n = senate.length();
		for(int i = 0; i < n; i++){
			if(senate.charAt(i) == 'R'){
				qr.add(i);
			}else{
				qd.add(i);
			}
		}
		while(!qr.isEmpty() && !qd.isEmpty()){
			int r = qr.poll();
			int d = qd.poll();
			if(r < d){
				qr.add(r + n);
			}else{
				qd.add(d + n);
			}
		}
		return qr.size() > qd.size() ? "Radiant" : "Dire";
	}
}
