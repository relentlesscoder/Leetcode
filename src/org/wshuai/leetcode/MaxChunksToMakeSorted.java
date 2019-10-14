package org.wshuai.leetcode;

/**
 * Created by Wei on 10/14/2019.
 * #769 https://leetcode.com/problems/max-chunks-to-make-sorted/
 */
public class MaxChunksToMakeSorted {
	public int maxChunksToSorted(int[] arr) {
		int res = 0;
		int[] count = new int[10];
		for(int i = 0; i < arr.length; i++){
			count[arr[i]]++;
			boolean split = true;
			for(int j = 0; j <= i; j++){
				if(count[j] == 0){
					split = false;
					break;
				}
			}
			if(split){
				res++;
			}
		}
		return res;
	}
}
