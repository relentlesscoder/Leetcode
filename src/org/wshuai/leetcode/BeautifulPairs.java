package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 01/08/2025.
 * #2613 https://leetcode.com/problems/beautiful-pairs/
 */
public class BeautifulPairs {

    // time O(n * log(n)), space O(n)
    public int[] beautifulPair(int[] nums1, int[] nums2) {
        int[] sorted = Arrays.stream(nums2).distinct().sorted().toArray();
        int n = nums1.length, m = sorted.length;
        // Sort the new array nums[i] = {nums1[i], nums2[i], i} by nums[i][0]
        // so when we iterate the array, the current nums[i][0] >= nums[j][0]
        // where j is indexes that are already processed.
        // Now |nums1[i] - nums1[j]| + |nums2[i] - nums2[j]| can be divided into
        // two cases:
        //   1. nums2[i] >= nums2[j] -> nums1[i] - nums1[j] + nums2[i] - nums2[j]
        //      -> (nums1[i] + nums2[i]) - (nums1[j] + nums2[j])
        //   2. nums2[i] <= nums2[j] -> nums1[i] - nums1[j] + nums2[j] - nums2[i]
        //      -> (nums1[i] - nums2[i]) - (nums1[j] - nums2[j])
        // Use two BITs (with nums2[i] as index) to store maximum (nums1[j] + nums2[j])
        // and (nums1[j] - nums2[j]) to make the result minimum. Note that for second
        // case we need to do reverse map for BIT index since nums2[i] <= nums2[j]
        int[][] nums = new int[n][3];
        Arrays.setAll(nums, i -> new int[]{nums1[i], nums2[i], i});
        Arrays.sort(nums, (a, b) -> a[0] - b[0]);
        BIT bit1 = new BIT(m), bit2 = new BIT(m);
        int min = Integer.MAX_VALUE, idx1 = -1, idx2 = -1;
        for (int i = 0; i < n; i++) {
            // Handle case 1
            int val = nums[i][0] + nums[i][1];
            int index = binarySearch(sorted, nums[i][1] + 1);
            int[] res = bit1.query(index);
            if (res[1] != Integer.MAX_VALUE) {
                int diff = val - res[0];
                int id1 = Math.min(res[1], nums[i][2]);
                int id2 = Math.max(res[1], nums[i][2]);
                if (diff < min || (diff == min && (idx1 > id1 || (idx1 == id1 && idx2 > id2)))) {
                    min = diff;
                    idx1 = id1;
                    idx2 = id2;
                }
            }
            bit1.update(index, val, nums[i][2]);
            // Handle case 2
            val = nums[i][0] - nums[i][1];
            index = m + 1 - binarySearch(sorted, nums[i][1] + 1);
            res = bit2.query(index);
            if (res[1] != Integer.MAX_VALUE) {
                int diff = val - res[0];
                int id1 = Math.min(res[1], nums[i][2]);
                int id2 = Math.max(res[1], nums[i][2]);
                if (diff < min || (diff == min && (idx1 > id1 || (idx1 == id1 && idx2 > id2)))) {
                    min = diff;
                    idx1 = id1;
                    idx2 = id2;
                }
            }
            bit2.update(index, val, nums[i][2]);
        }
        return new int[] {idx1, idx2};
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
        private final int[] idx;

        public BIT(int n) {
            tree = new int[n + 1];
            Arrays.fill(tree, Integer.MIN_VALUE);
            idx = new int[n + 1];
        }

        public void update(int index, int val, int i) {
            while (index < tree.length) {
                if (tree[index] < val || tree[index] == val && idx[index] > i) {
                    tree[index] = val;
                    idx[index] = i;
                }
                index += index & -index;
            }
        }

        public int[] query(int index) {
            // Set res[0] default to Integer.MIN_VALUE + 1 to avoid both tree[index]
            // and res[0] is Integer.MIN_VALUE, which then will go inside the if condition
            int[] res = new int[] { Integer.MIN_VALUE + 1, Integer.MAX_VALUE};
            while (index > 0) {
                if (tree[index] > res[0] || tree[index] == res[0] && idx[index] < res[1]) {
                    res[0] = tree[index];
                    res[1] = idx[index];
                }
                index -= index & -index;
            }
            return res;
        }
    }
}
