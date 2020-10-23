package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 06/28/2017.
 * #0218 https://leetcode.com/problems/the-skyline-problem/
 */
public class TheSkylineProblem {

	// time O(n*log(n)), space O(n)
	// https://briangordon.github.io/2014/08/the-skyline-problem.html
	public List<List<Integer>> getSkyline(int[][] buildings) {
		List<List<Integer>> res = new ArrayList<>();
		if(buildings == null || buildings.length == 0){
			return res;
		}
		int n = buildings.length*2, i = 0;
		int[][] events = new int[n][2];
		for(int[] b : buildings){
			events[i++] = new int[]{b[0], -b[2]};
			events[i++] = new int[]{b[1], b[2]};
		}
		// break tie logic to handle case like [1 3 5], [3 7 5] - 3 should not be a critical point
		Arrays.sort(events, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
		TreeMap<Integer, Integer> heightMap = new TreeMap<>();
		heightMap.put(0, 1);
		int max = 0;
		for(int[] e : events){
			if(e[1] < 0){ // add building
				heightMap.put(-e[1], heightMap.getOrDefault(-e[1], 0) + 1);
			}else{ // remove building
				int count = heightMap.get(e[1]);
				if(--count > 0){
					heightMap.put(e[1], count);
				}else{
					heightMap.remove(e[1]);
				}
			}
			// critical points that change the current max forms the skyline
			int cur = heightMap.lastKey();
			if(cur != max){
				res.add(Arrays.asList(e[0], cur));
				max = cur;
			}
		}
		return res;
	}

	// time O(n*log(n)), space O(n)
	public List<List<Integer>> getSkylineMergeSort(int[][] buildings) {
		return mergeSort(buildings, 0, buildings.length - 1);
	}

	private LinkedList<List<Integer>> mergeSort(int[][] buildings, int l, int r){
		LinkedList<List<Integer>> res = new LinkedList<>();
		if(l > r){
			return res;
		}
		if(l == r){
			res.add(Arrays.asList(buildings[l][0], buildings[l][2]));
			res.add(Arrays.asList(buildings[l][1], 0));
			return res;
		}
		int m = l + (r - l) / 2;
		LinkedList<List<Integer>> left = mergeSort(buildings, l, m);
		LinkedList<List<Integer>> right = mergeSort(buildings, m + 1, r);
		int leftHeight = 0, rightHeight = 0;
		while(!left.isEmpty() || !right.isEmpty()){
			long x1 = left.isEmpty() ? Long.MAX_VALUE : left.peekFirst().get(0);
			long x2 = right.isEmpty() ? Long.MAX_VALUE : right.peekFirst().get(0);
			int x = 0;
			if(x1 < x2){
				List<Integer> temp = left.pollFirst();
				x = temp.get(0);
				leftHeight = temp.get(1);
			}else if(x1 > x2){
				List<Integer> temp = right.pollFirst();
				x = temp.get(0);
				rightHeight = temp.get(1);
			}else{
				x = left.peekFirst().get(0);
				leftHeight = left.pollFirst().get(1);
				rightHeight = right.pollFirst().get(1);
			}
			int h = Math.max(leftHeight, rightHeight);
			if(res.isEmpty() || h != res.peekLast().get(1)){
				res.add(Arrays.asList(x, h));
			}
		}
		return res;
	}

	/*
	public List<List<Integer>> getSkylinePriorityQueue(int[][] buildings) {
		List<List<Integer>> res = new ArrayList<>();
		if(buildings == null || buildings.length == 0){
			return res;
		}
		int n = buildings.length*2, i = 0;
		int[][] events = new int[n][2];
		for(int[] b : buildings){
			events[i++] = new int[]{b[0], -b[2]};
			events[i++] = new int[]{b[1], b[2]};
		}
		// break tie logic is important to handle case like [1 3 5], [3 7 5] - 3 should not be a critical point
		Arrays.sort(events, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
		pq.offer(0);
		for(int[] e : events){
			int max = pq.peek();
			if(e[1] < 0){ // add building
				pq.offer(-e[1]);
			}else{
				pq.remove(e[1]); // remove building, time O(n)
			}

			if(pq.peek() != max){ // critical points that change the current max forms the skyline
				res.add(Arrays.asList(e[0], pq.peek()));
			}
		}
		return res;
	}*/
}
