package org.wshuai.leetcode.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 12/30/2025.
 * #2602 https://leetcode.com/problems/minimum-operations-to-make-all-array-elements-equal/
 */
public class MinimumOperationsToMakeAllArrayElementsEqual {

    // time O((m + n) * log(n)), space O(n)
    public List<Long> minOperations(int[] nums, int[] queries) {
        // 一图流: https://leetcode.cn/problems/minimum-operations-to-make-all-array-elements-equal/solutions/2191417/yi-tu-miao-dong-pai-xu-qian-zhui-he-er-f-nf55/
        // 把原数组排序，遍历询问数组对每个询问用二分找到数组中第一个大于等于
        // 询问的索引 idx 。利用前缀和数组分别计算 idx 左边和右边需要的操作
        // 数。
        List<Long> res = new ArrayList<>();
        int n = nums.length;
        // 排序
        Arrays.sort(nums); // O(n * log(n))
        long[] prefix = new long[n + 1];
        // 计算数组前缀和
        for (int i = 0; i < n; i++) { // O(n)
            prefix[i + 1] = prefix[i] + nums[i];
        }
        // 遍历询问
        for (int q : queries) { // O(m)
            // 二分
            int idx = binarySearch(nums, q); // O(log(n))
            // idx 左边所有的数需要增加的操作，则总操作数为:
            //   q * idx - sum[0, idx - 1]
            long cnt1 = (long) q * idx - prefix[idx];
            // idx 及其右边所有的数需要减少的操作，总操作数为:
            //   sum[idx, n - 1] - q * (n - idx)
            long cnt2 = 0;
            if (n > idx) {
                cnt2 = prefix[n] - prefix[idx] - (long) (n - idx) * q;
            }
            res.add(cnt1 + cnt2);
        }
        return res;
    }

    private int binarySearch(int[] nums, int target) {
        int low = 0, high = nums.length;
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
