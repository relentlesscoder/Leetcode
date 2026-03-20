package org.wshuai.leetcode.dp;

/**
 * Created by Wei on 10/09/2025.
 * #1671
 * https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array/
 */
public class MinimumNumberOfRemovalsToMakeMountainArray {

    // time O(n * log(n)), space O(n)
    public int minimumMountainRemovals(int[] nums) {
        // 核心思路：山脉数组 = 先严格递增再严格递减
        // 找到以每个位置为山顶时，左侧 LIS 长度 + 右侧 LIS 长度（反向）- 1 = 山脉长度
        // 最少删除数 = n - 最长山脉长度
        int n = nums.length, len = 0;
        // left[i] = 以 nums[i] 结尾的最长严格递增子序列长度（从左到右）
        int[] left = lisAtEachPosition(nums);
        // 反转数组，从右到左求 LIS，等价于求原数组从右到左的最长严格递减子序列
        reverse(nums);
        // right[j] = 反转后以位置 j 结尾的 LIS 长度
        // 对应原数组位置 n-1-j，即以 nums[n-1-j] 开头的最长严格递减子序列长度
        int[] right = lisAtEachPosition(nums);
        // 枚举每个位置 i 作为山顶
        for (int i = 0; i < n; i++) {
            // 山顶左右两侧都必须有元素（LIS 长度 > 1），否则不构成山脉
            if (left[i] <= 1 || right[n - 1 - i] <= 1) {
                continue;
            }
            // 以 i 为山顶的最长山脉长度 = 左侧 LIS + 右侧 LIS - 1（山顶被算了两次）
            len = Math.max(len, left[i] + right[n - 1 - i] - 1);
        }
        // 最少删除数 = 总长度 - 最长山脉长度
        return n - len;
    }

    private void reverse(int[] nums) {
        // 双指针反转数组
        for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    private int[] lisAtEachPosition(int[] nums) {
        // 求每个位置结尾的 LIS 长度（贪心 + 二分）
        // res[i] = 以 nums[i] 结尾的最长严格递增子序列长度
        int n = nums.length;
        // res[i] 记录结果，arr 为贪心数组（arr[k] = 长度为 k+1 的 LIS 的最小结尾值）
        int[] res = new int[n], arr = new int[n];
        for (int i = 0, high = 0; i < n; i++) {
            // lower bound: 找 arr 中第一个 >= nums[i] 的位置（严格递增，相等必须替换）
            int idx = binarySearch(arr, high, nums[i]);
            // idx 就是 nums[i] 能接在的 LIS 长度，所以以 nums[i] 结尾的 LIS 长度 = idx + 1
            res[i] = idx + 1;
            if (idx == high) {
                // nums[i] > arr 中所有元素，追加到末尾，延长 LIS
                arr[high++] = nums[i];
            } else {
                // 用 nums[i] 替换 arr[idx]，使结尾值更小
                arr[idx] = nums[i];
            }
        }
        return res;
    }

    private int binarySearch(int[] nums, int high, int target) {
        // lower bound: 找 nums[0..high) 中第一个 >= target 的位置
        int low = 0;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
