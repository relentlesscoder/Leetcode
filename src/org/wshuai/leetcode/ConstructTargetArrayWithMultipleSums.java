package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 02/18/2020.
 * #1354 https://leetcode.com/problems/construct-target-array-with-multiple-sums/
 */
public class ConstructTargetArrayWithMultipleSums {
	public boolean isPossible(int[] target) {
		if(target.length == 1){
			return target[0] == 1;
		}
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
		long sum = 0;
		for(int t : target){
			sum += t;
			pq.offer(t);
		}
		while(pq.peek() > sum / 2){
			int max = pq.poll();
			if(sum - max == 1){
				return true;
			}
			if(sum == max){
				return false;
			}
			int prev = max % (int)(sum - max); // prev = max - (sum - max)
			pq.offer(prev);
			sum += prev - max;
		}
		return sum == target.length;
	}
}
