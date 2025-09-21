package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 04/26/2020.
 * #1424 https://leetcode.com/problems/diagonal-traverse-ii/
 */
public class DiagonalTraverseII {

	// time O(d), space O(d)
	public int[] findDiagonalOrderHashMap(List<List<Integer>> nums) {
		int count = 0, k = 0;
		Map<Integer, LinkedList<Integer>> map = new HashMap<>();
		for(int i = 0; i < nums.size(); i++){
			List<Integer> cur = nums.get(i);
			for(int j = 0; j < cur.size(); j++){
				map.putIfAbsent(i + j, new LinkedList<>());
				map.get(i + j).offerFirst(cur.get(j));
				count++;
			}
		}
		int[] res = new int[count];
		for(int i = 0; i < map.size(); i++){
			for(int num : map.get(i)){
				res[k++] = num;
			}
		}
		return res;
	}

	// time O(d*log(d)), space O(d)
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

	// time O(m*n)
	// TLE
	public int[] findDiagonalOrder(List<List<Integer>> nums) {
		int count = 0, r = nums.size(), c = 0;
		for(List<Integer> list : nums){
			count += list.size();
			c = Math.max(c, list.size());
		}
		int[] res = new int[count];
		int index = 0;
		for(int k = 0; k < r; k++){
			for(int i = k, j = 0; i >= 0 && j < c; i--, j++){
				if(j < nums.get(i).size()){
					res[index++] = nums.get(i).get(j);
				}
			}
		}
		for(int k = 1; k < c; k++){
			for(int i = r - 1, j = k; i >= 0 && j < c; i--, j++){
				if(j < nums.get(i).size()){
					res[index++] = nums.get(i).get(j);
				}
			}
		}
		return res;
	}
}
