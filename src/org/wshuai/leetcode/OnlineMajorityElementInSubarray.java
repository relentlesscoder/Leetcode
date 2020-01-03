package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 12/14/2019.
 * #1157 https://leetcode.com/problems/online-majority-element-in-subarray/
 */
public class OnlineMajorityElementInSubarray {
	private int[] arr;
	private int n;
	private int[][] segmentTree;
	private Map<Integer, List<Integer>> map;

	public OnlineMajorityElementInSubarray(int[] arr) {
		n = arr.length;
		if(n > 0){
			this.arr = arr;
			int x = (int)Math.ceil(Math.log(n)/Math.log(2));
			int size = 2 * (int)Math.pow(2, x) - 1;
			segmentTree = new int[size][2];
			build(0, n - 1, 0);
			map = new HashMap<>();
			for(int i = 0; i < arr.length; i++){
				map.putIfAbsent(arr[i], new ArrayList<>());
				map.get(arr[i]).add(i);
			}
		}
	}

	public int query(int left, int right, int threshold) {
		int[] cand = queryUtil(0, n - 1, left, right, 0);
		if(cand[1] == 0){
			return -1;
		}
		List<Integer> list = map.get(cand[0]);
		int l = BinarySearchUtil.searchLeft(list, left);
		int r = BinarySearchUtil.searchRight(list, right);
		return r - l + 1 >= threshold ? cand[0] : -1;
	}

	private void build(int start, int end, int index){
		if(start == end){
			segmentTree[index] = new int[]{arr[start], 1};
			return;
		}
		int mid = start + (end - start) / 2;
		build(start, mid, 2 * index + 1);
		build(mid + 1, end, 2 * index + 2);
		segmentTree[index] = merge(segmentTree[2 * index + 1], segmentTree[2 * index + 2]);
	}

	private int[] merge(int[] a, int[] b){
		if(a[0] == b[0]){
			return new int[]{a[0], a[1] + b[1]};
		}
		if(a[1] > b[1]){
			return new int[]{a[0], a[1] - b[1]};
		}
		return new int[]{b[0], b[1] - a[1]};
	}

	private int[] queryUtil(int start, int end, int left, int right, int index){
		if(left > end || right < start){
			return new int[]{0, 0};
		}
		if(left <= start && right >= end){
			return segmentTree[index];
		}
		int mid = start + (end - start) / 2;
		int[] a = queryUtil(start, mid, left, right, 2 * index + 1);
		int[] b = queryUtil(mid + 1, end, left, right, 2 * index + 2);
		return merge(a, b);
	}
}

class OnlineMajorityElementInSubarrayRandomPick {
	private int[] arr;
	private int n;
	private Map<Integer, List<Integer>> map;

	public OnlineMajorityElementInSubarrayRandomPick(int[] arr) {
		map = new HashMap<>();
		this.arr = arr;
		n = arr.length;
		for(int i = 0; i < arr.length; i++){
			map.putIfAbsent(arr[i], new ArrayList<>());
			map.get(arr[i]).add(i);
		}
	}

	public int query(int left, int right, int threshold) {
		for(int i = 0; i < 10; i++){
			int rand = left + (new Random().nextInt(right - left + 1));
			int a = arr[rand];
			List<Integer> lst = map.get(a);
			int l = BinarySearchUtil.searchLeft(lst, left);
			int r = BinarySearchUtil.searchRight(lst, right);
			if(r - l + 1 >= threshold){
				return a;
			}
		}
		return -1;
	}
}

class BinarySearchUtil{
	public static int searchLeft(List<Integer> lst, int t){
		int l = 0;
		int r = lst.size() - 1;
		while(l < r){
			int mid = l + (r - l) / 2;
			if(lst.get(mid) < t){
				l = mid + 1;
			}else{
				r = mid;
			}
		}
		return l;
	}

	public static int searchRight(List<Integer> lst, int t){
		int l = 0;
		int r = lst.size() - 1;
		while(l < r){
			int mid = l + (r - l + 1) / 2;
			if(lst.get(mid) > t){
				r = mid - 1;
			}else{
				l = mid;
			}
		}
		return l;
	}
}
