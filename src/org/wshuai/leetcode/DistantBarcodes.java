package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 11/6/19.
 * #1054 https://leetcode.com/problems/distant-barcodes/
 */
public class DistantBarcodes {
	public int[] rearrangeBarcodes(int[] barcodes) {
		PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] == b[1] ? a[0] - b[0] : b[1] - a[1]);
		int[] count = new int[10_001];
		for(int i : barcodes){
			count[i]++;
		}
		for(int i = 1; i < 10_001; i++){
			if(count[i] > 0){
				queue.offer(new int[]{i, count[i]});
			}
		}
		int[] res = new int[barcodes.length];
		int k = 0;
		while(!queue.isEmpty()){
			int[] f = queue.poll();
			res[k++] = f[0];
			f[1]--;
			if(!queue.isEmpty()){
				int[] s = queue.poll();
				res[k++] = s[0];
				s[1]--;
				if(s[1] > 0){
					queue.offer(s);
				}
			}
			if(f[1] > 0){
				queue.offer(f);
			}
		}
		return res;
	}
}
