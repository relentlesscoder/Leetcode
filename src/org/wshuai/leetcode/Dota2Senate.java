package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 11/12/2019.
 * #0649 https://leetcode.com/problems/dota2-senate/
 */
public class Dota2Senate {
	// time O(n), space O(n)
	public String predictPartyVictory(String senate) {
		int n = senate.length();
		LinkedList<Integer> radiants = new LinkedList<>(), dires = new LinkedList<>();
		for(int i = 0; i < n; i++){
			if(senate.charAt(i) == 'R'){
				radiants.offerLast(i);
			}else{
				dires.offerLast(i);
			}
		}
		while(!radiants.isEmpty() && !dires.isEmpty()){
			int rad = radiants.pollFirst(), dir = dires.pollFirst();
			if(rad < dir){
				radiants.offerLast(rad + n);
			}else{
				dires.offerLast(dir + n);
			}
		}
		return radiants.size() > dires.size() ? "Radiant" : "Dire";
	}
}
