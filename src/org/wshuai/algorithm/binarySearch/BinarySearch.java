package org.wshuai.algorithm.binarySearch;

public class BinarySearch {
	public int binarySearch(int[] arr, int x){
		int left = 0;
		int right = arr.length - 1;
		while(left < right){
			int mid = left + (right - left) / 2;
			if(arr[mid] >= x){
				right = mid;
			}else{
				left = mid + 1;
			}
		}
		return arr[left] == x ? left : -1;
	}
}
