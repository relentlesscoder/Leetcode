package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 12/26/2019.
 * #0699 https://leetcode.com/problems/falling-squares/
 */
public class FallingSquares {
	private int[] tree;
	private int n;

	// time O(n*log(n)), space O(n)
	public List<Integer> fallingSquares(int[][] positions) {
		Integer[] res = new Integer[positions.length];
		TreeSet<Integer> set = new TreeSet<>();
		for(int[] pos : positions){
			set.add(pos[0]);
			// -1 for the right side
			set.add(pos[0] + pos[1] - 1);
		}
		Map<Integer, Integer> map = new HashMap<>();
		int j = 0;
		for(int s : set){
			map.put(s, j++);
		}
		n = map.size();
		int x = (int)Math.ceil(Math.log(n)/Math.log(2));
		int size = 2 * (int)Math.pow(2, x) - 1;
		tree = new int[size];

		int i = 0;
		int curMax = 0;
		for(int[] pos : positions){
			int left = map.get(pos[0]);
			int right = map.get(pos[0] + pos[1] - 1);
			int maxHeight = query(left, right) + pos[1];
			update(left, right, maxHeight);
			curMax = Math.max(curMax, maxHeight);
			res[i++] = curMax;
		}
		return Arrays.asList(res);
	}

	private int query(int left, int right){
		return queryUtil(0, n - 1, left, right, 0);
	}

	private int queryUtil(int start, int end, int left, int right, int index){
		if(left > end || right < start){
			return 0;
		}
		if(start >= left && end <= right){
			return tree[index];
		}
		int mid = start + (end - start) / 2;
		int leftMax = queryUtil(start, mid, left, right, 2 * index + 1);
		int rightMax = queryUtil(mid + 1, end, left, right, 2 * index + 2);
		return Math.max(leftMax, rightMax);
	}

	private void update(int left, int right, int val){
		updateUtil(0, n - 1, left, right, 0, val);
	}

	private void updateUtil(int start, int end, int left, int right, int index, int val){
		if(start > end || start > right || end < left){
			return;
		}
		tree[index] = Math.max(tree[index], val);
		if(start != end){
			int mid = start + (end - start) / 2;
			updateUtil(start, mid, left, right, 2 * index + 1, val);
			updateUtil(mid + 1, end, left, right, 2 * index + 2, val);
		}
	}
}
