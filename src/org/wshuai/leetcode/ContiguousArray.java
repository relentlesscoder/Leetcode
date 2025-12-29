package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 02/27/2017.
 * #0525 https://leetcode.com/problems/contiguous-array/
 */
public class ContiguousArray {

    // time O(n), space O(n)
    public int findMaxLengthHashMap(int[] nums) {
        // 遍历数组，对结束与每个位置的前缀数组计算其中1和0的差(后面简称为
        // 10差)则这个范围在[-n, n]之内。如果两个前缀的10差相同，说明它们
        // 相减得到的子数组包含相同个数的1和0.
        // 示例1: [0,1,1,1,1,1,0,0,0]
        // 索引2的10差为2 - 1 = 1 而索引8的10差为5 - 4 = 1，所以子数组
        // [3, 8]是一个满足要求的子数组，答案即为所有这种子数组的最大长度。
        int res = 0, n = nums.length;
		// 哈希表的键是10而差值是对应的索引。
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0, diff = 0; i < n; i++) {
            diff += nums[i] == 1 ? 1 : -1;
            if (map.containsKey(diff)) {
                res = Math.max(res, i - map.get(diff));
            }
            // 越长的子数组越可能成为答案，所以我们只记录第一个10差的索引。
            map.putIfAbsent(diff, i);
        }
        return res;
    }

    // time O(n), space O(n)
    public int findMaxLengthArray(int[] nums) {
        // 用数组代替哈希表
        int res = 0, n = nums.length;
        int[] count = new int[2 * n + 1];
        Arrays.fill(count, n);
        count[n] = -1;
        for (int i = 0, diff = 0; i < n; i++) {
            diff += nums[i] == 1 ? 1 : -1;
            if (count[diff + n] != n) {
                res = Math.max(res, i - count[diff + n]);
            } else {
                count[diff + n] = i;
            }
        }
        return res;
    }
}
