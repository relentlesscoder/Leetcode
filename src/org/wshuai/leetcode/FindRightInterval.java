package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * Created by Wei on 11/12/2016.
 * #0436 https://leetcode.com/problems/find-right-interval/
 */
public class FindRightInterval {
	// time O(n*log(n))
	public int[] findRightInterval(int[][] intervals) {
		if(intervals == null || intervals.length == 0){
			return new int[0];
		}
		int n = intervals.length;
		int[] res = new int[n];
		int[][] starts = new int[n][2];
		for(int i = 0; i < n; i++){
			starts[i] = new int[]{intervals[i][0], i};
		}
		Arrays.sort(starts, (a, b) -> a[0] - b[0]);
		for(int i = 0; i < n; i++){
			int index = binarySearch(starts, intervals[i][1]);
			res[i] = index < n ? starts[index][1] : -1;
		}
		return res;
	}

	private int binarySearch(int[][] starts, int target){
		int left = 0, right = starts.length;
		while(left < right){
			int mid = left + (right - left) / 2;
			if(starts[mid][0] < target){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		return left;
	}

	// time O(n*log(n))
	public int[] findRightIntervalTreeMap(int[][] intervals) {
		if(intervals == null || intervals.length == 0){
			return new int[0];
		}
		int n = intervals.length;
		int[] res = new int[n];
		TreeMap<Integer, Integer> map = new TreeMap<>();
		for(int i = 0; i < n; i++){
			map.put(intervals[i][0], i);
		}
		for(int i = 0; i < n; i++){
			Integer key = map.ceilingKey(intervals[i][1]);
			res[i] = key == null ? -1 : (int)map.get(key);
		}
		return res;
	}
}
