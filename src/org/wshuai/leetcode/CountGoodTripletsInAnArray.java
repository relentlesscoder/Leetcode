package org.wshuai.leetcode;

/**
 * Created by Wei on 11/05/2025.
 * #2179 https://leetcode.com/problems/count-good-triplets-in-an-array/
 */
public class CountGoodTripletsInAnArray {

    // time O(n * log(n)), space O(n)
    public long goodTriplets(int[] nums1, int[] nums2) {
        long res = 0;
        int n = nums1.length;
        int[] map = new int[n];
        // Map values in nums1 to [0, 1, ..., n - 1]
        // For example, nums1 = [4,0,1,3,2] is mapped to [0,1,2,3,4]
        for (int i = 0; i < n; i++) {
            map[nums1[i]] = i;
        }
        BIT bit = new BIT(n);
        // For each value in nums2, find the mapped value based on the mapping.
        // For example, nums2 = [4,1,0,2,3] is mapped to [0,2,1,4,3]
        // Now we just need to find the longest increasing subsequence with length 3
        // between two arrays.
        // So we iterate nums2, for each index nums2[i], let's say the mapped value is
        // val. If there is less_v number of values on its left that are less than val
        // then there will be (n - 1 - val - (i - less_v)) number of values on its right
        // that are greater than val.
        // Therefore, total number of valid triplets with nums2[i] as its middle element
        // is less_v * (n - 1 - val - (i - less_v))
        for (int i = 0; i < n; i++) {
            int index = map[nums2[i]];
            int count = bit.query(index + 1);
            res += 1L * count * (n - 1 - index - (i - count));
            bit.update(index + 1);
        }
        return res;
    }

    private static class BIT {

        private int[] tree;

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
