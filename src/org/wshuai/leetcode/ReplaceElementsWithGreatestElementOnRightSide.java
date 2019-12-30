package org.wshuai.leetcode;

/**
 * Created by Wei on 12/30/2019.
 * #1299 https://leetcode.com/problems/replace-elements-with-greatest-element-on-right-side/
 */
public class ReplaceElementsWithGreatestElementOnRightSide {
	public int[] replaceElements(int[] arr) {
		int n = arr.length;
		int max = arr[n - 1];
		arr[n - 1] = -1;
		for(int i = n - 2; i >= 0; i--){
			int temp = arr[i];
			arr[i] = max;
			max = Math.max(max, temp);
		}
		return arr;
	}
}
