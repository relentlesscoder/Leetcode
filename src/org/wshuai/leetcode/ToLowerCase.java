package org.wshuai.leetcode;

/**
 * Created by Wei on 08/07/2019.
 * #0709 https://leetcode.com/problems/to-lower-case/
 */
public class ToLowerCase {
	// time O(n)
	public String toLowerCase(String str) {
		if(str == null || str.isEmpty()){
			return "";
		}
		char[] arr = str.toCharArray();
		int diff = 'a' - 'A';
		for(int i = 0; i < arr.length; i++){
			if(arr[i] >= 'A' && arr[i] <= 'Z'){
				arr[i] = (char)(arr[i] + diff);
			}
		}
		return new String(arr);
	}
}
