package org.wshuai.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/20/2020.
 * #1590 https://leetcode.com/problems/make-sum-divisible-by-p/
 */
public class MakeSumDivisibleByP {

    // time O(n), space O(n)
    public int minSubarray(int[] nums, int p) {
		// 假设 sum 为数组的元素和且 s 为去掉的子数组的和，则
		// (sum - s) % p == 0 所以 sum 和 s 对 p 同余，即
		// sum % p == s % p。将 s 表示为两个前缀和的差
		// s[r] - s[l] 则有 sum % p == (s[r] - s[l]) % p。
		// 等同于 (s[r] % p - sum % p + p) % p = s[l] % p。
		// 示例1:
		//   (10 - 8) % 3 = 20 % 3
		//   (10 % 3 - 20 % 3 + 3) % 3 = 8 % 3
        int n = nums.length, res = n, mod = 0;
		// 遍历数组计算 sum % p
        for (int num : nums) {
            mod = (mod + num) % p;
        }
		// 数组和可以整除 p 则无需删除任何子数组
        if (mod == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0, sum = 0; i < n; i++) {
            sum = (sum + nums[i]) % p; // 计算 s[r] % p
            int key = (sum - mod + p) % p; // 计算 (s[r] % p - sum % p + p) % p
            if (map.containsKey(key)) {
                res = Math.min(res, i - map.get(key));
            }
            map.put(sum, i);
        }
		// 注意要保证这里长度不为 n ，因为不允许删除整个数组。当 s[r] = sum
		// 的时候等式(s[r] % p - sum % p + p) % p= 0 肯定成立。
        return res == n ? -1 : res;
    }
}
