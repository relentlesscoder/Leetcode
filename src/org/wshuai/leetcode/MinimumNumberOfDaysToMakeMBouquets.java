package org.wshuai.leetcode;

import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Created by Wei on 06/14/2020.
 * #5455 https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/
 */
public class MinimumNumberOfDaysToMakeMBouquets {

	// time O(n*log(n)), space O(n)
	public int minDays(int[] bloomDay, int m, int k) {
		TreeMap<Integer, Integer> treeMap = new TreeMap<>();
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		int n = bloomDay.length, count = 0;
		for(int i = 0; i < n; i++){
			pq.offer(new int[]{bloomDay[i], i});
		}
		while(!pq.isEmpty()){
			int[] cur = pq.poll();
			Integer floorKey = treeMap.floorKey(cur[1]), ceilKey = treeMap.ceilingKey(cur[1]);
			boolean mergeHead = (floorKey != null && treeMap.get(floorKey) == cur[1] - 1),
				mergeTail = (ceilKey != null && ceilKey == cur[1] + 1);
			if(mergeTail && mergeHead){
				int tail = treeMap.get(ceilKey), head = treeMap.get(floorKey);
				count -= (tail - ceilKey + 1) / k;
				count -= (head - floorKey + 1) / k;
				count += (tail - floorKey + 1) / k;
				treeMap.remove(ceilKey);
				treeMap.put(floorKey, tail);
			}else if(mergeTail){
				int tail = treeMap.get(ceilKey);
				count -= (tail - ceilKey + 1) / k;
				count += (tail - cur[1] + 1) / k;
				treeMap.remove(ceilKey);
				treeMap.put(cur[1], tail);
			}else if(mergeHead){
				int head = treeMap.get(floorKey);
				count -= (head - floorKey + 1) / k;
				count += (cur[1] - floorKey + 1) / k;
				treeMap.put(floorKey, head + 1);
			}else{
				count += 1 / k;
				treeMap.put(cur[1], cur[1]);
			}
			if(count >= m){
				return cur[0];
			}
		}
		return -1;
	}
}
