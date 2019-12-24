package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 12/23/2019.
 * #1298 https://leetcode.com/problems/maximum-candies-you-can-get-from-boxes/
 */
public class MaximumCandiesYouCanGetFromBoxes {
	public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
		int n = status.length;
		int res = 0;
		LinkedList<Integer> queue = new LinkedList<>();
		boolean[] opened = new boolean[n];
		boolean[] unopened = new boolean[n];
		for(int ib : initialBoxes){
			queue.offerLast(ib);
			unopened[ib] = true;
		}
		while(!queue.isEmpty()){
			int cur = queue.pollFirst();
			if(status[cur] == 1 && !opened[cur]){
				res += candies[cur];
				opened[cur] = true;
				for(int k : keys[cur]){
					status[k] = 1;
					if(unopened[k]){
						queue.offerLast(k);
					}
				}
				for(int cb : containedBoxes[cur]){
					unopened[cb] = true;
					queue.offerLast(cb);
				}
			}
		}
		return res;
	}
}
