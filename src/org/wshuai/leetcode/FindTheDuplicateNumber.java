package org.wshuai.leetcode;

/**
 * Created by Wei on 01/29/2017.
 * #0287 https://leetcode.com/problems/find-the-duplicate-number/
 */
public class FindTheDuplicateNumber {
	// time O(n)
	// same as #0142 https://leetcode.com/problems/linked-list-cycle/
	public int findDuplicate(int[] nums) {
		if(nums == null || nums.length == 0){
			return 0;
		}
		int slow = nums[0], fast = nums[0];
		while(true){
			slow = nums[slow];
			fast = nums[nums[fast]];
			if(fast == slow){
				break;
			}
		}
		fast = nums[0];
		while(fast != slow){
			slow = nums[slow];
			fast = nums[fast];
		}
		return slow;
	}
}
