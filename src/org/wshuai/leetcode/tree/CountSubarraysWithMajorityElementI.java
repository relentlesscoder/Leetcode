package org.wshuai.leetcode.tree;

import java.util.Arrays;

/**
 * Created by Wei on 12/29/2025.
 * #3737 https://leetcode.com/problems/count-subarrays-with-majority-element-i/
 */
public class CountSubarraysWithMajorityElementI {

    // time O(n), space O(n)
    public int countMajoritySubarraysPrefixSum(int[] nums, int target) {
        // #3739的值域缩小版，题解见#3739
        int res = 0, n = nums.length;
        int[] freq = new int[(n << 1) + 1];
        freq[n]++;
        for (int i = 0, sum = 0, count = 0; i < n; i++) {
            if (nums[i] == target) {
                count += freq[sum + n];
                sum++;
            } else {
                sum--;
                count -= freq[sum + n];
            }
            res += count;
            freq[sum + n]++;
        }
        return res;
    }

    // time O(n * log(m)), space O(n + m)
    public int countMajoritySubarraysBIT(int[] nums, int target) {
        // #3739的值域缩小版，题解见#3739
        int res = 0, n = nums.length;
        int[] vals = new int[n + 1];
        for (int i = 0, sum = 0; i < n; i++) {
            sum += (nums[i] == target ? 1 : -1);
            vals[i + 1] = sum;
        }
        int[] sorted = Arrays.stream(vals).distinct().sorted().toArray();
        int m = sorted.length;
        BIT bit = new BIT(m);
        bit.update(binarySearch(sorted, 0) + 1);
        for (int i = 0; i < n; i++) {
            int v = vals[i + 1];
            res += bit.query(binarySearch(sorted, v));
            bit.update(binarySearch(sorted, v) + 1);
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

    private static class BIT {

        private final int[] tree;

        public BIT(int n) {
            tree = new int[n + 1];
        }

        public void update(int index) {
            while (index < tree.length) {
                tree[index]++;
                index += index & -index;
            }
        }

        public int query(int index) {
            int res = 0;
            while (index > 0) {
                res += tree[index];
                index -= index & -index;
            }
            return res;
        }
    }
}
