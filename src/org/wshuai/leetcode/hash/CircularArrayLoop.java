package org.wshuai.leetcode.hash;

/**
 * Created by Wei on 11/12/2019.
 * #0457 https://leetcode.com/problems/circular-array-loop/
 */
public class CircularArrayLoop {

    // time O(n), space O(1)
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                continue;
            }
			// 快慢指针
            int slow = i, fast = next(i, nums, n);
			// 检查快指针的当前和下一步是否与慢指针正负一致
            while (nums[fast] * nums[slow] > 0
                    && nums[next(fast, nums, n)] * nums[slow] > 0) {
                if (fast == slow) {
                    if (slow == next(slow, nums, n)) { // k = 1
                        break;
                    } else {
                        return true;
                    }
                }
				// 快二慢一
                fast = next(next(fast, nums, n), nums, n);
                slow = next(slow, nums, n);
            }
			// 如果到达这里说明基于 i 的路径不可行，将路径上所有的点设为 0 以免重复遍历。
            int add = i;
            while (nums[add] * nums[next(add, nums, n)] > 0) {
                int temp = add;
                add = next(add, nums, n);
                nums[temp] = 0;
            }
        }
        return false;
    }

    private int next(int i, int[] nums, int n) {
        return ((i + nums[i]) % n + n) % n;
    }
}
