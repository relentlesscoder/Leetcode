package org.wshuai.leetcode;

/**
 * Created by Wei on 08/31/2020.
 * #1566 https://leetcode.com/problems/detect-pattern-of-length-m-repeated-k-or-more-times/
 */
public class DetectPatternOfLengthMRepeatedKOrMoreTimes {

	// time O(n^3)
	public boolean containsPattern(int[] arr, int m, int k) {
		for(int i = 0; i + m * k <= arr.length; i++){
			boolean valid = true;
			for(int j = i; j < i + m; j++){
				int val = arr[j];
				for(int s = 1; s < k; s++){
					if(arr[j + s * m] != val){
						valid = false;
						break;
					}
				}
				if(!valid){
					break;
				}
			}
			if(valid){
				return true;
			}
		}
		return false;
	}
}
