package org.wshuai.algorithm.binarySearch;

import org.junit.Test;

public class BinarySearch {

	// search for the first match or insert position is no match
	public int binarySearchLeftBound(int[] arr, int x){
		int left = 0;
		int right = arr.length - 1;
		while(left < right){
			int mid = left + (right - left) / 2;
			if(arr[mid] < x){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		return left;
	}

	public int binarySearchLeftFirstBigger(int[] arr, int x){
		int left = 0;
		int right = arr.length - 1;
		while(left < right){
			int mid = left + (right - left) / 2;
			if(arr[mid] < x){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		return arr[left] == x ? left + 1 : left;
	}

	@Test
	public void testcase(){
		BinarySearch bs = new BinarySearch();
		int left1 = bs.binarySearchLeftBound(new int[]{1, 2, 4, 5, 7, 8}, 3);
		int left2 = bs.binarySearchLeftBound(new int[]{1, 2, 4, 5, 7, 8}, 4);
		int left3 = bs.binarySearchLeftFirstBigger(new int[]{1, 2, 4, 5, 7, 8}, 3);
		int left4 = bs.binarySearchLeftFirstBigger(new int[]{1, 2, 4, 5, 7, 8}, 4);
	}
}
