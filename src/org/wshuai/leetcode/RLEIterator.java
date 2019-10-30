package org.wshuai.leetcode;

/**
 * Created by Wei on 10/30/2019.
 * #900 https://leetcode.com/problems/rle-iterator/
 */
public class RLEIterator {
	private int[] data;
	// use long to avoid integer overflow
	private long[] sum;
	private long count;

	// binary search
	public RLEIterator(int[] A) {
		count = 0;
		sum = new long[A.length / 2];
		data = A;
		long curr = 0;
		int j = 0;
		for(int i = 0; i < data.length; i += 2){
			curr += data[i];
			sum[j++] = curr;
		}
	}

	public int next(int n) {
		count += n;
		int left = 0;
		int right = sum.length - 1;
		while(left < right){
			int mid = left + (right - left) / 2;
			if(sum[mid] < count){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		if(sum[right] < count){
			return -1;
		}
		return data[right * 2 + 1];
	}
}
