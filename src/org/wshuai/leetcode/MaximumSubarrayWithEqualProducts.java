package org.wshuai.leetcode;

/**
 * Created by Wei on 12/01/2025.
 * #3411 https://leetcode.com/problems/maximum-subarray-with-equal-products/
 */
public class MaximumSubarrayWithEqualProducts {

    // time O(n), space O(1)
    public int maxLengthSlidingWindow(int[] nums) {
        // https://leetcode.cn/problems/maximum-subarray-with-equal-products/solutions/3039079/mei-ju-ti-qian-tui-chu-xun-huan-pythonja-a21k/
        int res = 2, n = nums.length, prod = 1;
        for (int left = 0, right = 0; right < n; right++) {
            while (gcd(prod, nums[right]) > 1) {
                prod /= nums[left++];
            }
            prod *= nums[right];
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    // time O(n^2 * log(MAX)), space O(1)
    public int maxLength(int[] nums) {
        int res = 0, n = nums.length, max = -1, allLcm = 1;
        for (int num : nums) { // O(n)
            max = Math.max(max, num); // Maximum GCD
            allLcm = lcm(allLcm, num); // Maximum LCM
        }
        for (int i = 0; i < n; i++) { // O(n)
            int prod = 1, g = 0, l = 1;
            // Add constraint prod <= allLcm * max to avoid integer overflow
            for (int j = i; j < n && prod <= allLcm * max; j++) { // O(n)
                int v = nums[j];
                prod *= v;
                g = gcd(g, v); // O(min(log(a), log(b)))
                l = lcm(l, v);
                if (prod == l * g) {
                    res = Math.max(res, j - i + 1);
                }
            }
        }
        return res;
    }

    public int gcd(int a, int b) {
        while (a != 0) {
            int temp = a;
            a = b % a;
            b = temp;
        }
        return b;
        // return a == 0 ? b : gcd(b % a, a);
    }

    public int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }
}
