package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by Wei on 9/30/2019.
 * #1090 https://leetcode.com/problems/largest-values-from-labels/
 */
public class LargestValuesFromLabels {
	public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
		int res = 0;
		Map<Integer, Integer> label_count = new HashMap<>();
		PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[0] - a[0]);
		for(int i = 0; i < values.length; i++){
			queue.offer(new int[]{values[i], labels[i]});
		}
		int count = 0;
		while(count < num_wanted && !queue.isEmpty()){
			int[] next = queue.poll();
			int c = label_count.getOrDefault(next[1], 0);
			if(c >= use_limit){
				continue;
			}
			res += next[0];
			label_count.put(next[1], c + 1);
			count++;
		}
		return res;
	}
}
