package org.wshuai.leetcode;

/**
 * Created by Wei on 01/29/2017.
 * #0287 https://leetcode.com/problems/find-the-duplicate-number/
 */
public class FindTheDuplicateNumber {

    // time O(n), space O(1)
    public int findDuplicate(int[] nums) {
        // #0142 快慢指针
        // https://leetcode.cn/problems/find-the-duplicate-number/solutions/3797843/yong-ji-huan-shu-li-jie-zuo-fa-tong-142-tkoc2/
        int slow = 0, fast = 0, head = 0;
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) {
                break;
            }
        }
        while (slow != head) {
            slow = nums[slow];
            head = nums[head];
        }
        return slow;
    }

    // time O(n), space O(1)
    public int findDuplicateCyclicSort(int[] nums) {
        // 循环排序
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[i];
                nums[i] = nums[nums[i] - 1];
                nums[temp - 1] = temp;
            }
            if (nums[i] != i + 1) {
                return nums[i];
            }
        }
        return -1;
    }
}
