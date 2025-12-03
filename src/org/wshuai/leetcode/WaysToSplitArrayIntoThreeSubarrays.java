package org.wshuai.leetcode;

/**
 * Created by Wei on 04/27/2021.
 * #1712 https://leetcode.com/problems/ways-to-split-array-into-three-subarrays/
 */
public class WaysToSplitArrayIntoThreeSubarrays {

    private static final int MOD = (int) 1e9 + 7;

    public int waysToSplitSlidingWindow(int[] nums) {
        long res = 0;
        int n = nums.length;
        /*  s[] is prefix sum array
            s[l] <= s[r] - s[l] <= s[n] - s[r]
            -> s[r] >= 2 * s[l]
            -> s[l] >= 2 * s[r] - s[n]
            -> s[r] >= 2 * (2 * s[r] - s[n]) => 2 * s[n] >= 3 * s[r] (r和n的另一层关系)
        */
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        // [left1, left2) is the range for valid left split point for each right split
        // point
        for (int left1 = 1, left2 = 1, right = 2;
             right < n && 2 * prefix[n] >= 3 * prefix[right]; right++) {
            // 固定[r, n)为right区间,则mid区间为[left, r)
            // 每次循环,即求解对固定的r有多少个合法的left值
            int rightSum = prefix[n] - prefix[right];
            // 区间和mid <= right条件,限制mid不能过宽,即left下界
            // 每次循环初始,由于r的右移使mid扩大,right减小,可能使mid <= right由合法->不合法
            // 移动left1,使left1成为第一个让mid <= right条件(合法)的分割点
            while (left1 < right - 1 && prefix[right] - prefix[left1] > rightSum) {
                left1++;
            }
            // 区间和left <= mid条件,限制mid不能过窄,即left上界
            // 每次循环初始,由于r的右移使mid扩大,left不变,可能使left <= mid由不合法->合法
            // 移动left2,使left2成为第一个让left <= mid条件(不合法)的分割点
            while (left2 < right && prefix[left2] <= prefix[right] - prefix[left2]) {
                left2++;
            }
            // [left1, left2)即为mid区间左端点left的所有可能取值
            res += Math.max(left2 - left1, 0);
        }
        return (int) (res % MOD);
    }

    // time O(n * log(n)), space O(n)
    public int waysToSplitBinarySearch(int[] nums) {
        int res = 0, n = nums.length;
        int[] prefix = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }
        for (int i = 1; i < n - 1; i++) {
            if (prefix[i] > (prefix[n] - prefix[i]) / 2) {
                break;
            }
            int left = findLeft(prefix, prefix[i], n, i);
            int right = findRight(prefix, n, i);
            if (left == -1 || right == -1) {
                continue;
            }
            res = (res + (right - left + 1) % MOD) % MOD;
        }
        return res;
    }

    private int findLeft(int[] prefix, int leftSum, int n, int index) {
        int left = index, right = n - 2;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int sum = prefix[mid + 1] - prefix[index];
            if (sum < leftSum) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return prefix[left + 1] - prefix[index] >= leftSum ? left : -1;
    }

    private int findRight(int[] prefix, int n, int index) {
        int left = index, right = n - 2;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            int midSum = prefix[mid + 1] - prefix[index], rightSum = prefix[prefix.length - 1] - prefix[mid + 1];
            if (midSum > rightSum) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return prefix[prefix.length - 1] - prefix[left + 1] >= prefix[left + 1] - prefix[index] ? left : -1;
    }
}
