package org.wshuai.leetcode;

/**
 * Created by Wei on 01/12/2020.
 * #1313 https://leetcode.com/problems/decompress-run-length-encoded-list/
 */
public class DecompressRunLengthEncodedList {
	// time O(n)
	public int[] decompressRLElist(int[] nums) {
		int cnt = 0, k = 0;
		for(int i = 0; i < nums.length; i += 2){
			cnt += nums[i];
		}
		int[] res = new int[cnt];
		for(int i = 0; i < nums.length; i += 2){
			for(int j = 0; j < nums[i]; j++){
				res[k++] = nums[i + 1];
			}
		}
		return res;
	}
}
