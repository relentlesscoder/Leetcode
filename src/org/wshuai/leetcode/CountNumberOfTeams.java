package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 03/29/2020.
 * #1395 https://leetcode.com/problems/count-number-of-teams/
 */
public class CountNumberOfTeams {

    // time O(n * log(n)), space O(n)
    public int numTeamsBinaryIndexedTree(int[] rating) {
        int res = 0, n = rating.length;
        int[] sorted = Arrays.stream(rating).distinct().sorted().toArray(); // O(n * log(n))
        // For each index i, map[i] is number of ratings less than rating[i] on its left.
		// Since all ratings are distinct, number of ratings greater than rating[i] on its left
		// is i - map[i].
        int[] map = new int[n];
        BIT bit = new BIT(n);
        for (int i = 0; i < n; i++) { // O(n)
            int index = binarySearch(sorted, rating[i]), less = bit.query(index); // O(log(n))
            map[i] = less;
            bit.update(index + 1); // O(log(n))
        }
        bit = new BIT(n);
        for (int i = n - 1; i >= 0; i--) { // O(n)
            int index = binarySearch(sorted, rating[i]), less = bit.query(index); // O(log(n))
            res += (i - map[i]) * less + map[i] * (n - 1 - i - less);
            bit.update(index + 1); // O(log(n))
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

    // time O(n^2), space O(n)
    public int numTeams(int[] rating) {
        int res = 0, n = rating.length;
        // asc[i] denotes number of LIS ends with rating[i] exclusively
        // desc[i] denotes number of LDS ends with rating[i] exclusively
        int[] asc = new int[n], desc = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (rating[i] > rating[j]) {
                    // asc[j] = 0 means no triplet ends with i
                    // asc[j] = 1 means 1 triplet ends with i
                    // asc[j] = 2 means 2 triplet ends with i
                    // ...
                    res += asc[j];
                    asc[i]++;
                }
                if (rating[i] < rating[j]) {
                    res += desc[j];
                    desc[i]++;
                }
            }
        }
        return res;
    }
}
