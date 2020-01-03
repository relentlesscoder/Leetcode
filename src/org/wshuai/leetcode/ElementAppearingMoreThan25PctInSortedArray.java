package org.wshuai.leetcode;

/**
 * Created by Wei on 12/15/2019.
 * #1287 https://leetcode.com/problems/element-appearing-more-than-25-in-sorted-array/
 */
public class ElementAppearingMoreThan25PctInSortedArray {
	public int findSpecialInteger(int[] arr) {
		int threshold = arr.length / 4;
		int x = (int)Math.ceil(arr.length / 4.0);
		int[] candidates = new int[]{
			x - 1,
			2 * x - 1,
			3 * x - 1,
			Math.min(4 * x - 1, arr.length)
		};
		for(int c : candidates){
			if(checkNumber(arr[c], arr, threshold)){
				return arr[c];
			}
		}
		return -1;
	}

	private boolean checkNumber(int num, int[] arr, int threshold){
		int left = searchLeft(num, arr);
		int right = searchRight(num, arr);
		return right - left + 1 > threshold;
	}

	private int searchLeft(int num, int[] arr){
		int l = 0;
		int r = arr.length - 1;
		while(l < r){
			int mid = l + (r - l) / 2;
			if(arr[mid] < num){
				l = mid + 1;
			}else{
				r = mid;
			}
		}
		return l;
	}

	private int searchRight(int num, int[] arr){
		int l = 0;
		int r = arr.length - 1;
		while(l < r){
			int mid = l + (r - l + 1) / 2;
			if(arr[mid] > num){
				r = mid - 1;
			}else{
				l = mid;
			}
		}
		return l;
	}
}
