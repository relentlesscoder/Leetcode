package org.wshuai.leetcode;

import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Created by Wei on 06/14/2020.
 * #1482 https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/
 */
public class MinimumNumberOfDaysToMakeMBouquets {

	// time O(log(max)), space O(1)
	public int minDays(int[] bloomDay, int m, int k) {
		if (m * k > bloomDay.length) {
			return -1;
		}
		int low = 1, high = 0;
		for (int d : bloomDay) {
			high = Math.max(high, d);
		}
		while (low < high) {
			int mid = (low + high) >> 1;
			if (canMake(bloomDay, m, k, mid)) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return canMake(bloomDay, m, k, low) ? low : -1; // need to validate since it is possible even the max value is still impossible to make m bouquets
	}

	private boolean canMake(int[] bloomDay, int m, int k, int mid) {
		int bouquets = 0, count = 0;
		for (int i = 0; i < bloomDay.length; i++) {
			if (bloomDay[i] > mid) {
				count = 0;
			} else if (++count == k) {
				count = 0;
				if (++bouquets == m) {
					return true;
				}
			}
		}
		return false;
	}

	/*
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
	} */
}
