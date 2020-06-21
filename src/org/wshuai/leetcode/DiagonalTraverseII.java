package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 04/26/2020.
 * #1424 https://leetcode.com/problems/diagonal-traverse-ii/submissions/
 */
public class DiagonalTraverseII {

	// time O(m*n), space O(m*n)
	public int[] findDiagonalOrderHashMap(List<List<Integer>> nums) {
		Map<Integer, LinkedList<Integer>> map = new HashMap<>();
		int m = nums.size(), count = 0;
		for(int i = 0; i < m; i++){
			List<Integer> cur = nums.get(i);
			for(int j = 0; j < cur.size(); j++){
				if(j >= nums.get(i).size()){
					continue;
				}
				map.putIfAbsent(i + j, new LinkedList<>());
				map.get(i + j).offerFirst(cur.get(j));
				count++;
			}
		}
		int[] res = new int[count];
		int k = 0;
		for(int i = 0; i < map.size(); i++){
			for(int j : map.get(i)){
				res[k++] = j;
			}
		}
		return res;
	}

	// time O(m*n*log(m*n)), space O(m*n)
	public int[] findDiagonalOrderPriorityQueue(List<List<Integer>> nums) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->
			a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
		for(int i = 0; i < nums.size(); i++){
			List<Integer> row = nums.get(i);
			for(int j = 0; j < row.size(); j++){
				pq.offer(new int[]{j, i + j, row.get(j)});
			}
		}
		int[] res = new int[pq.size()];
		int i = 0;
		while(!pq.isEmpty()){
			res[i++] = pq.poll()[2];
		}
		return res;
	}

	// time O(m*n), space O(1)
	// TLE
	public int[] findDiagonalOrderMatrixPositioning(List<List<Integer>> nums) {
		int m = nums.size(), n = 0, count = 0;
		for(int i = 0; i < m; i++){
			n = Math.max(n, nums.get(i).size());
			count += nums.get(i).size();
		}
		int[] res = new int[count];
		int k = 0;
		for(int s = 0; s < m; s++){
			for(int i = s, j = 0; i >= 0; i--, j++){
				if(j >= nums.get(i).size()){
					continue;
				}
				res[k++] = nums.get(i).get(j);
			}
		}
		for(int s = 1; s < n; s++){
			for(int i = m - 1, j = s; i >= 0; i--, j++){
				if(j >= nums.get(i).size()){
					continue;
				}
				res[k++] = nums.get(i).get(j);
			}
		}

		return res;
	}
}
