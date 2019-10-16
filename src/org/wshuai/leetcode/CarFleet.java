package org.wshuai.leetcode;

import java.util.TreeMap;

/**
 * Created by Wei on 10/16/2019.
 * #853 https://leetcode.com/problems/car-fleet/
 */
public class CarFleet {

	// see https://leetcode.com/problems/car-fleet/discuss/139850/C%2B%2BJavaPython-Straight-Forward
	public int carFleet(int target, int[] position, int[] speed) {
		TreeMap<Integer, Double> m = new TreeMap<>();
		for(int i = 0; i < position.length; i++){
			m.put(-position[i], (double)(target - position[i]) / speed[i]);
		}
		int res = 0;
		double curr = 0;
		for(double time : m.values()){
			if(time > curr){
				curr = time;
				res++;
			}
		}
		return res;
	}

}
