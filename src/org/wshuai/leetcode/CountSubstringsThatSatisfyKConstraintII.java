package org.wshuai.leetcode;

/**
 * Created by Wei on 12/02/2025.
 * #3261 https://leetcode.com/problems/count-substrings-that-satisfy-k-constraint-ii/
 */
public class CountSubstringsThatSatisfyKConstraintII {

    // time O(n + m * log(n)), space O(n)
    public long[] countKConstraintSubstrings(String s, int k, int[][] queries) {
        int n = s.length(), m = queries.length;
        // left[i] is the min left index for all valid k-constraint substrings
        // in [left[i], i]
        int[] left = new int[n];
        // sum[i] is the prefix sum for all valid k-constraint substrings ends at
        // indexes in range [0, i - 1]
        long[] sum = new long[n + 1];
        // Calculate left[i] and sum[i] using the same idea as #3258
        for (int i = 0, j = 0, zeros = 0, ones = 0; i < n; i++) { // O(n)
            int curr = s.charAt(i) - '0';
            zeros += 1 - curr;
            ones += curr;
            while (zeros > k && ones > k) {
                int head = s.charAt(j++) - '0';
                zeros -= 1 - head;
                ones -= head;
            }
            // left[i] is the min index for i to form a valid substring s[left[i], i]
            left[i] = j;
            sum[i + 1] = sum[i] + i - j + 1;
        }
        // Now we have two different cases for processing queries, I will use QL and QR to
        // denote left index and right index for a query:
        //   1. left[i] <= QL: in this case all k-constraint substrings in [QL, QR] is valid
        //      since they all together form a subset to [left[i], i] where i in range
        //      [QL, QR]. Note that due to the monotonic attribute here, any index i' left to
        //      i in range [QL, QR] can only have a smaller left[i'] the left[i]. The total of
        //      substring is:
        //        (r - l + 1) * (r - l + 2) / 2
        //   2. left[i] > QL: in this case we need to use binary search to find the first index
        //      j that has left[j] > QL, then based on the analysis in case 1 all substrings in
        //      indexes range [QL, j - 1] are valid since they have the left index <= QL.
        //      The total of these substrings is:
        //        (j - l + 1) * (j - l + 2) / 2
        //      For substrings with indexes in [j, QR] they all have left index > QL, the total
        //      number of valid k-constraint substrings is the sum of valid substrings end at each
        //      index:
        //        sum(endAt(j), endAt(j + 1), ... , endAt(QL))
        //      which can be found with prefix sum array in constant time.
        long[] res = new long[m];
        for (int i = 0; i < m; i++) { // O(m)
            int start = queries[i][0], end = queries[i][1];
            // Find the first index i that has left[i] > QL or return QL
            int idx = binarySearch(left, start, end + 1, start); // O(log(n))
            res[i] = sum[end + 1] - sum[idx] + (long) (idx - start + 1) * (idx - start) / 2;
        }
        return res;
    }

    private int binarySearch(int[] nums, int left, int right, int target) {
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
