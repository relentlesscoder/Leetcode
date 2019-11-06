package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wei on 11/6/19.
 * #1215 https://leetcode.com/problems/stepping-numbers/
 */
public class SteppingNumbers {
	public List<Integer> countSteppingNumbers(int low, int high) {
		ArrayList<Integer> res = new ArrayList<>();
		if(low > high){
			return res;
		}

		LinkedList<Long> queue = new LinkedList<>();
		for(long i = 1; i <= 9; i++){
			queue.offerLast(i);
		}
		if(low == 0){
			res.add(0);
		}
		while(!queue.isEmpty()){
			long v = queue.pollFirst();
			if(v < high){
				long last = v % 10;
				if(last > 0){
					queue.add(v * 10 + last - 1);
				}
				if(last < 9){
					queue.add(v * 10 + last + 1);
				}
			}
			if(v >= low && v <= high){
				res.add((int)v);
			}
		}
		return res;
	}
}
