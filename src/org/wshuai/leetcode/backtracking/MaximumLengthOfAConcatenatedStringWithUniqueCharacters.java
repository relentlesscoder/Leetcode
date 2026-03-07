package org.wshuai.leetcode.backtracking;

import java.util.List;

/**
 * Created by Wei on 10/31/2019.
 * #1239 https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
 */
public class MaximumLengthOfAConcatenatedStringWithUniqueCharacters {

    private int res = 0;

    // time O(2^n), space O(n)
    public int maxLength(List<String> arr) {
        res = 0;
        int n = arr.size();
        // 用一个整型表示字符串中含有的字符
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            int curr = 0;
            for (char c : arr.get(i).toCharArray()) {
                int shift = c - 'a';
                // 注意如果字符串本身含有重复字符则不能被用来串联
                if (((1 << shift) & curr) > 0) {
                    curr = 0;
                    break;
                }
                curr |= 1 << (shift);
            }
            nums[i] = curr;
        }
        dfs(0, 0, nums);
        return res;
    }

    private void dfs(int i, int mask, int[] nums) {
        if (i == nums.length) {
            res = Math.max(res, Integer.bitCount(mask));
            return;
        }
        // 不串联当前字符串
        dfs(i + 1, mask, nums);
        if ((mask & nums[i]) == 0) { // 判断当前字符串与已构造的字符串是否有交集
            // 没有交集则可以串联当前字符串
            dfs(i + 1, mask | nums[i], nums);
        }
    }
}
