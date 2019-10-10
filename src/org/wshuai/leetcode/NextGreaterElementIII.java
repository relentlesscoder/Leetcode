package org.wshuai.leetcode;

/**
 * Created by Wei on 10/8/2019.
 * #556 https://leetcode.com/problems/next-greater-element-iii/
 */
public class NextGreaterElementIII {
	public int nextGreaterElement(int n) {
		// example 3046321
		char[] a = ("" + n).toCharArray();
		int i = a.length - 2;
		// find the first number (4) from right that is not in descending order
		while(i >= 0 && a[i + 1] <= a[i]){
			i--;
		}
		if(i < 0){
			return -1;
		}
		/**
		int j = a.length - 1;
		// find the first element (6) from right to swap with 4
		while(j >= 0 && a[j] <= a[i]){
			j--;
		}
		 **/
		// swap -> 3064321
		swap(a, i,i+1);
		// sort the right side to get the smallest -> 3061234
		reverse(a, i + 1);
		try{
			return Integer.parseInt(new String(a));
		}catch(Exception e){
			return -1;
		}
	}

	private void reverse(char[] a, int start){
		int i = start, j = a.length - 1;
		while(i < j){
			swap(a, i, j);
			i++;
			j--;
		}
	}

	private void swap(char[] a, int i, int j){
		char tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
}
