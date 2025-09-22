package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 06/22/2025.
 * #3152 https://leetcode.com/problems/special-array-ii/
 */
public class SpecialArrayII {

    // time O(m + n), space O(m + n)
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        boolean[] res = new boolean[m];
        // use endIndex array to store furthest index it can reach to
        // form a special subarray from the current index i
        int[] endIndex = new int[n];
        endIndex[n - 1] = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (((nums[i] & 1) ^ (nums[i + 1] & 1)) == 0) {
                endIndex[i] = i;
            } else {
                endIndex[i] = endIndex[i + 1];
            }
        }
        for (int i = 0; i < m; i++) {
            res[i] = endIndex[queries[i][0]] >= queries[i][1];
        }
        return res;
    }

    // time O(m + n), space O(m + n)
    public boolean[] isArraySpecialPrefixSum(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        boolean[] res = new boolean[m];
        // use prefix array to store count of violating numbers
        int[] prefix = new int[n];
        prefix[0] = 0;
        for (int i = 1; i < n; i++) {
            if (((nums[i] & 1) ^ (nums[i - 1] & 1)) == 0) {
                prefix[i] = prefix[i - 1] + 1;
            } else {
                prefix[i] = prefix[i - 1];
            }
        }
        for (int i = 0; i < m; i++) {
            // note that the first number of a subarray is always valid since
            // it does not have a previous number to compare in this subarray.
            // So we can use prefix[q[1]] - prefix[q[0]]
            res[i] = prefix[queries[i][1]] == prefix[queries[i][0]];
        }
        return res;
    }

    // time O(m * log(n)), space O(m + n)
    public boolean[] isArraySpecialBinarySearch(int[] nums, int[][] queries) {
        int n = queries.length, i = 1, j = 0;
        boolean[] res = new boolean[n];
        List<int[]> map = new ArrayList<>();
        for ( ; i < nums.length; i++) {
            if (((nums[i] & 1) ^ (nums[i - 1] & 1)) == 0) {
                if (i - 1 > j) {
                    map.add(new int[]{j, i - 1});
                }
                j = i;
            }
        }
        if (i - 1 > j) {
            map.add(new int[]{j, i - 1});
        }
        for (int k = 0; k < n; k++) {
            if (queries[k][0] == queries[k][1]) {
                res[k] = true;
            } else {
                res[k] = isSpecial(map, queries[k]);
            }
        }
        return res;
    }

    private boolean isSpecial(List<int[]> map, int[] query) {
        int low = 0, high = map.size() - 1;
        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            if (map.get(mid)[0] > query[0]) {
                high = mid - 1;
            } else {
                low = mid;
            }
        }
        return low != map.size()
                && map.get(low)[0] <= query[0]
                && map.get(low)[1] >= query[1];
    }
}
