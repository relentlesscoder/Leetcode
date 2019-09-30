package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 9/30/2019.
 * #1151 https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together/
 */
public class MinimumSwapsToGroupAllOnesTogether {
	// sliding window
	public int minSwaps(int[] data) {
		int count = 0;
		for(int i = 0; i < data.length; i++){
			if(data[i] == 1){
				count++;
			}
		}
		if(count <= 1){
			return 0;
		}
		int min = Integer.MAX_VALUE;
		LinkedList<Integer> queue = new LinkedList<Integer>();
		int i = 0;
		int curr = 0;
		while(i < data.length){
			if(queue.size() == count){
				min = Math.min(min, count - curr);
				int head = queue.pollFirst();
				curr -= head == 1 ? 1 : 0;
			}
			curr += data[i] == 1 ? 1 : 0;
			queue.offerLast(data[i]);
			i++;
		}
		return min;
	}
}
