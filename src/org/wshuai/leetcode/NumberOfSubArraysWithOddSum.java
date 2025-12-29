package org.wshuai.leetcode;

/**
 * Created by Wei on 07/28/2020.
 * #1524 https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum/
 */
public class NumberOfSubArraysWithOddSum {

    private static final int MOD = (int) 1e9 + 7;

    // time O(n), space O(1)
    public int numOfSubarraysPrefixSum(int[] arr) {
		// 性质：不同奇偶性的两数的差为奇数，相同奇偶性的两数的差为偶数
		// 维护两个变量even和odd分别表示偶数和奇数前缀和的数量。遍历数
		// 组对每个位置计算当前前缀和并根据其奇偶性和前述两个变量的值来
		// 计算奇数子数组的个数。
        long res = 0, even = 0, odd = 0;
        int n = arr.length;
        for (int i = 0, sum = 0; i < n; i++) {
            sum += arr[i];
			// 当前前缀和为奇数，可以和所有偶数前缀和产生一个奇数和子数组
            if ((sum & 1) == 1) {
				res += even + 1; // 注意当前前缀和本身也是合法的所以要+1
                odd++; // 维护奇数前缀和数量
            } else { // 当前前缀和为偶数，可以和所有奇数前缀和产生一个奇数和子数组
                res += odd;
                even++; // 维护偶数前缀和数量
            }
        }
        return (int) (res % MOD);
    }

    // time O(n), space O(1)
    public int numOfSubarrays(int[] arr) {
        int res = 0, n = arr.length, prev = 0;
        prev = arr[0] % 2 == 1 ? 1 : 0;
        res += prev;
        for (int i = 1; i < n; i++) {
            int cur = 0;
            if (arr[i] % 2 == 1) {
                cur = (i - prev) + 1;
            } else {
                cur = prev;
            }
            res = (res + cur) % MOD;
            prev = cur;
        }
        return res;
    }

    // time O(n), space O(n)
    public int numOfSubarrays1D(int[] arr) {
        int res = 0, n = arr.length;
        int[] dp = new int[n];
        dp[0] = arr[0] % 2 == 1 ? 1 : 0;
        res += dp[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] % 2 == 1) {
                dp[i] = (i - dp[i - 1]) + 1;
            } else {
                dp[i] = dp[i - 1];
            }
            res = (res + dp[i]) % MOD;
        }
        return res;
    }
}
