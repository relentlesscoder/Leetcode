package org.wshuai.leetcode;

/**
 * Created by Wei on 03/14/2017.
 * #0275 https://leetcode.com/problems/h-index-ii/
 */
public class HIndexII {
	// time O(log(n))
	public int hIndex(int[] citations) {
		int n = citations.length, left = 0, right = n;
		while(left < right){
			int mid = left + (right - left) / 2;
			if(citations[mid] == n - mid){
				return n - mid;
			}else if(citations[mid] < n - mid){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		return n - left;
	}
}
