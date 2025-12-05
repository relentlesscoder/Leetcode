package org.wshuai.leetcode;

/**
 * Created by Wei on 07/31/2025.
 * #2358 https://leetcode.com/problems/maximum-number-of-groups-entering-a-competition/
 */
public class MaximumNumberOfGroupsEnteringACompetition {

    // time O(1), space O(1)
    public int maximumGroups(int[] grades) {
        // 1 + 2 + ... + k <= n
        // k(k + 1) / 2 <= n
        // (k + 0.5)(k + 0.5) <= n * 2 + 0.25
        // k + 0.5 <= sqrt(n * 2 + 0.25)
        // k <= sqrt(n * 2 + 0.25) - 0.5
        return (int)(Math.sqrt(grades.length * 2 + 0.25) - 0.5);
    }

    // time O(log(1000)), space O(1)
    public int maximumGroupsBinarySearch(int[] grades) {
        int low = 0, high = 1000, n = grades.length;
        while (low < high) {
            int mid = (low + high + 1) >> 1;
            if (mid * (mid + 1) / 2 <= n) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    // time O(sqrt(n)), space O(1)
    public int maximumGroupsIterative(int[] grades) {
        int res = 0, size = 1, n = grades.length;
        for (int i = 0; i + size <= n; i += size, size++) {
            res++;
        }
        return res;
    }
}
