package org.wshuai.leetcode;

/**
 * Created by Wei on 01/24/2017.
 * #0484 https://leetcode.com/problems/find-permutation/
 */
public class FindPermutation {
	// time O(n)
	public int[] findPermutation(String s) {
		if(s == null || s.isEmpty()){
			return new int[0];
		}
		int n = s.length();
		char[] chars = s.toCharArray();
		int[] res = new int[n + 1];
		for(int i = 0; i <= n; i++){
			res[i] = i + 1;
		}
		for(int i = 0; i < n; i++){
			if(chars[i] == 'D'){
				int start = i, end = i;
				while(i < n && chars[i] == 'D'){
					end++;
					i++;
				}
				reverse(res, start, end);
				i--;
			}
		}
		return res;
	}

	private void reverse(int[] arr, int i, int j){
		while(i < j){
			int temp = arr[i];
			arr[i++] = arr[j];
			arr[j--] = temp;
		}
	}
}
