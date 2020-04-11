package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 03/23/2020.
 * #1386 https://leetcode.com/problems/cinema-seat-allocation/
 */
public class CinemaSeatAllocation {
	// time O(n), space O(n)
	public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
		int sum = 0, len = reservedSeats.length;
		int[] map = new int[]{2, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0};
		// use map to optimize performance for sparse input
		Map<Integer, Integer> rows = new HashMap<>();
		for(int j = 0; j < len; j++){
			int r = reservedSeats[j][0], v = reservedSeats[j][1], d = -1;
			// map row seats to 4 digits integer [0000, 1111], each digit represents two seats
			// 0 -> 2,3; 1 -> 4,5; 2 -> 6,7; 3 -> 8,9
			// other seats are not important
			if(v == 2 || v == 3){
				d = 0;
			}else if(v == 4 || v == 5){
				d = 1;
			}else if(v == 6 || v == 7){
				d = 2;
			}else if(v == 8 || v == 9){
				d = 3;
			}
			if(d != -1){
				rows.put(r, rows.getOrDefault(r, 0) | (1 << d));
			}
		}
		for(int val : rows.values()){
			sum += map[val];
		}
		// empty rows
		sum += 2 * (n - rows.size());
		return sum;
	}
}
