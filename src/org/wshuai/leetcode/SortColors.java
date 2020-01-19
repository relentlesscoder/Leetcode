package org.wshuai.leetcode;

/**
 * Created by Wei on 09/08/2016.
 * #0075 https://leetcode.com/problems/sort-colors/
 */
public class SortColors {
	// time O(n), one pass
	public void sortColors(int[] nums) {
		int r = 0, w = 0, b = 0;
		for(int i = 0; i < nums.length; i++){
			if(nums[i] == 0){
				nums[b++] = 2;
				nums[w++] = 1;
				nums[r++] = 0;
			}else if(nums[i] == 1){
				nums[b++] = 2;
				nums[w++] = 1;
			}else{
				nums[b++] = 2;
			}
		}
	}

	// time O(n)
	public void sortColorsOnePass(int[] nums) {
		int n = nums.length, j = 0, k = n - 1;
		for(int i = 0; i <= k; i++){
			if(nums[i] == 0 && i != j){
				// swap 0 with 1 on the left
				// i-- will keep the index at current
				// until the swap is done
				swap(nums, i--, j++);
			}else if(nums[i] == 2 && i != k){
				// swap 2 with 1 on the right
				swap(nums, i--, k--);
			}
		}
	}

	private void swap(int[] nums, int i, int j){
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	// time O(n)
	public void sortColorsTwoPass(int[] nums) {
		int n = nums.length, red = 0, blue = 0;
		for(int i = 0; i < n; i++){
			if(nums[i] == 0){
				nums[red++] = 0;
			}
			if(nums[i] == 2){
				blue++;
			}
		}
		for(int i = n - 1; i >= red; i--){
			if(blue-- > 0){
				nums[i] = 2;
			}else{
				nums[i] = 1;
			}
		}
	}
}
