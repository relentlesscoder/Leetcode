package org.wshuai.leetcode;

/**
 * Created by Wei on 09/24/2016.
 * #0274 https://leetcode.com/problems/h-index/
 */
public class HIndex {
	public int hIndex(int[] citations) {
		if(citations == null || citations.length == 0){
			return 0;
		}
		int n = citations.length;
		int[] buckets = new int[n + 1];
		for(int c : citations){
			if(c >= n){
				buckets[n]++;
			}else{
				buckets[c]++;
			}
		}
		int count = 0;
		for(int i = n; i >= 0; i--){
			count += buckets[i];
			if(count >= i){
				return i;
			}
		}
		return 0;
	}
}
