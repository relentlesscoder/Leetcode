package org.wshuai.leetcode;

/**
 * Created by Wei on 11/12/2019.
 * #0457 https://leetcode.com/problems/circular-array-loop/
 */
public class CircularArrayLoop {
	public boolean circularArrayLoop(int[] nums) {
		if(nums == null || nums.length < 2){
			return false;
		}
		int len = nums.length;
		for(int i = 0; i < len; i++){
			// skip visited nodes
			if(nums[i] == 0){
				continue;
			}
			// advance fast once to avoid erroneous check
			int slow = i, fast = advance(nums, slow);
			while(nums[i] * nums[fast] > 0 &&
				nums[i] * nums[advance(nums, fast)] > 0){
				if(slow == fast){
					// one element infinite loop
					if(slow == advance(nums, slow)){
						break;
					}
					return true;
				}
				slow = advance(nums, slow);
				fast = advance(nums, advance(nums, fast));
			}

			// set visited path to 0
			slow = i;
			int sign = nums[i];
			while(sign * nums[slow] > 0){
				int temp = advance(nums, slow);
				nums[slow] = 0;
				slow = temp;
			}
		}
		return false;
	}

	private int advance(int[] nums, int i){
		int len = nums.length;
		i += nums[i];
		i %= len;
		if(i < 0){
			i += len;
		}
		return i;
	}
}
