package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/01/2020.
 * #1552 https://leetcode.com/problems/magnetic-force-between-two-balls/
 */
public class MagneticForceBetweenTwoBalls {

	// time O(n*log(n))
	public int maxDistance(int[] position, int m) {
		Arrays.sort(position);
		int n = position.length, low = 0, high = position[n - 1];
		while(low <= high){
			int mid = low + (high - low) / 2;
			if(countBuckets(position, mid) >= m){
				low = mid + 1;
			}else{
				high = mid - 1;
			}
		}
		return high;
	}

	private int countBuckets(int[] position, int max){
		int last = position[0], count = 1;
		for(int i = 0; i < position.length; i++){
			if(position[i] - last >= max){
				count++;
				last = position[i];
			}
		}
		return count;
	}
}
