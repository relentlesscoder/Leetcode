package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/29/2019.
 * #0957 https://leetcode.com/problems/prison-cells-after-n-days/
 */
public class PrisonCellsAfterNDays {

	// time O(min(N, 2^n)), space O(2^n)
	public int[] prisonAfterNDays(int[] cells, int N) {
		Map<Integer, Integer> seen = new HashMap<>();
		boolean isFastForwarded = false;

		int stateBitmap = 0x0;
		for(int i = 0; i < cells.length; i++){
			stateBitmap <<= 1;
			stateBitmap = (stateBitmap | cells[i]);
		}

		while(N > 0){
			if(!isFastForwarded){
				if(seen.containsKey(stateBitmap)){
					N %= (seen.get(stateBitmap) - N); // the length of the cycle is seen[state_key] - N
					isFastForwarded = true;
				}else{
					seen.put(stateBitmap, N);
				}
			}

			// check if there is still some steps remained,
			// with or without the fast forwarding.
			if(N > 0){
				N--;
				stateBitmap = nextDay(stateBitmap);
			}
		}

		int[] res = new int[cells.length];
		for(int i = cells.length - 1; i >= 0; i--){
			res[i] = (stateBitmap & 0x1);
			stateBitmap >>= 1;
		}
		return res;
	}

	private int nextDay(int stateBitmap){
		// see picture on https://leetcode.com/problems/prison-cells-after-n-days/solution/
		stateBitmap = ~((stateBitmap << 1) ^ (stateBitmap >> 1));
		// set the head and tail to zero
		stateBitmap = stateBitmap & 0x7e; // 0x01111110
		return stateBitmap;
	}
}
