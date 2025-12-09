package org.wshuai.leetcode;

/**
 * Created by Wei on 01/07/2020.
 * #0034 https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

    // time O(log(n)), space O(1)
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        return new int[]{searchLower(nums, target), searchUpper(nums, target)};
    }

    private int searchLower(int[] nums, int target) {
		// 二分搜索下界（nums[i] >= target的最小值）模版：每次找到中间偏左的数与目标数比较。
		// 如果小于目标数，则丢弃区间 [low, mid]。反之则丢弃区间 [mid + 1, high]注意mid
		// 被保留因为nums[mid]是符合条件的。注意如果所有的数都不符合条件，low最终收敛在最后
		// 两个数右边的位置。可以观察数组中只有两个数的情况 [0,1]:
		//   1. 如果我们把high设为nums.length - 1，而且所有数字都小于目标数。那最终low和
		//   high会收敛在nums.length - 1。这样结束循环后我们还需要判断nums.length - 1的
		//   数是否符合条件。
		//   2. 如果我们把high设为nums.length，而且所有数字都小于目标数。那最终low和high会
		//   收敛在nums.length。这样结束循环后我们不需要多余判断因为所有的数都已经被检查过了。
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return nums[low] != target ? -1 : low;
    }

    private int searchUpper(int[] nums, int target) {
		// 二分搜索下界（nums[i] <= target的最大值）模版：每次找到中间偏右的数与目标数比较。
		// 如果大于目标数，则丢弃区间 [mid, high]。反之则丢弃区间 [low, mid - 1]注意mid
		// 被保留因为nums[mid]是符合条件的。注意如果所有的数都不符合条件，high最终收敛在最后
		// 两个数左边的位置。可以观察数组中只有两个数的情况 [0,1]:
		//   1. 如果我们把low设为0，而且所有数字都大于目标数。那最终low和high会收敛在0。这样
		//   结束循环后我们还需要判断在索引0的数是否符合条件。
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid;
            }
        }
        return nums[low] != target ? -1 : low;
    }
}
