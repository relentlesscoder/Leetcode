package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/05/2025.
 * #2122 https://leetcode.com/problems/recover-the-original-array/
 */
public class RecoverTheOriginalArray {

    // time O(n^2), space O(n)
    public int[] recoverArray(int[] nums) {
        int n = nums.length;
        int[] res = new int[n / 2];
        Arrays.sort(nums);
        for (int i = 1; i < n; i++) {
            // 由于 nums[0] = min - k，从1开始从左到右遍历数组假定当前数
            // nums[i] = min + k得到一个可能的k值。然后用这个k值从左到右
            // 遍历数组来寻找基于k的合法pairs，如果能遍历到n/2证明当前k值
            // 可行，返回当前的数组即可。
            int diff = nums[i] - nums[0];
            if (diff % 2 == 1 || diff == 0) {
                continue;
            }
            // 计算k值 nums[i] - nums[0] = min + k - min + k = 2 * k
            int k = diff >> 1;
            // 利用k来计算当前的最小值
            res[0] = nums[0] + k;
            int idx = 1;
            // 用一个布尔数组来记录已经找到的 nums[left] + 2 * k
            boolean[] visited = new boolean[n];
            visited[i] = true;
            // 遍历数组，为每一个 nums[left] 寻找 nums[left] + 2 * k
            for (int left = 1, right = i + 1; right < n; idx++, left++, right++) {
                while (left < n && visited[left]) {
                    left++;
                }
                // 注意 nums[right] < nums[left] + 2 * k 会有整数溢出的问题
                while (right < n && nums[right] - nums[left] < 2 * k) {
                    right++;
                }
                // 结束内循环如果已经无法找到与当前的 nums[left] 相对应的
                // nums[left] + 2 * k
                if (right == n || nums[right] - nums[left] > 2 * k) {
                    break;
                }
                visited[right] = true;
                // 注意如果用nums[right] - 2 * k会有整数溢出的问题
                // 另一种可行的写法是 nums[right] + nums[left] >> 1
                res[idx] = nums[left] + k;
            }
            // 找到一个合法答案，返回即可
            if (idx == n / 2) {
                return res;
            }
        }
        return res;
    }
}
