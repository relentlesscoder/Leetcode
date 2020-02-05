package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 06/28/2017.
 * #0218 https://leetcode.com/problems/the-skyline-problem/
 */
public class TheSkylineProblem {
	// time n*log(n)
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

	// time n*log(n)
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
		Arrays.sort(events, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
		pq.offer(0);
		for(int[] e : events){
			int max = pq.peek();
			if(e[1] < 0){
				pq.offer(-e[1]);
			}else{
				pq.remove(e[1]);
			}

			if(pq.peek() != max){
				res.add(Arrays.asList(e[0], pq.peek()));
			}
		}
		return res;
	}
}
