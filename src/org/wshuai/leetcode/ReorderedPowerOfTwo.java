package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/11/2019.
 * #869 https://leetcode.com/problems/reordered-power-of-2/
 */
public class ReorderedPowerOfTwo {
	public boolean reorderedPowerOf2(int N) {
		char[] arr = String.valueOf(N).toCharArray();
		Arrays.sort(arr);
		for(int i = 0; i < 31;i ++){
			char[] pow = String.valueOf((int)(1 << i)).toCharArray();
			Arrays.sort(pow);
			if(Arrays.equals(pow, arr)){
				return true;
			}
		}
		return false;
	}
}
