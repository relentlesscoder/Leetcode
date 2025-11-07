package org.wshuai.leetcode;

/**
 * Created by Wei on 11/07/2025.
 * #3312 https://leetcode.com/problems/sorted-gcd-pair-queries/
 */
public class SortedGcdPairQueries {

    // time O(n + (m + MAX) * log(MAX)), space O(MAX)
    public int[] gcdValues(int[] nums, long[] queries) {
        // https://leetcode.cn/problems/sorted-gcd-pair-queries/solutions/2940415/mei-ju-rong-chi-qian-zhui-he-er-fen-pyth-ujis/
        int m = queries.length, max = 0;
        int[] res = new int[m];
        for (int num : nums) { // O(n)
            max = Math.max(max, num);
        }
        int[] freq = new int[max + 1];
        long[] gcd = new long[max + 1];
        for (int num : nums) { // O(n)
            freq[num]++;
        }
        for (int i = max; i >= 1; i--) { // O(MAX)
            long c = 0;
            for (int j = i; j <= max; j += i) { // O(MAX)
                c += freq[j];
                gcd[i] -= gcd[j]; // Minus count of GCD for 2*i, 3*i, ...
            }
            // Number of total pairs (GCD = i) is selecting 2 numbers from all 1*i, 2*i, 3*i, ...
            // and minus count of pairs for GCD = 2*i, 3*i, ...
            gcd[i] += c * (c - 1) / 2;
        }
        // Calculate prefix sum
        for (int i = 1; i < gcd.length; i++) { // O(MAX)
            gcd[i] += gcd[i - 1];
        }
        // Binary search upper bound for queries[i] in GCD prefix sum array
        //   gcd_pair = [1,1,2,2,3,3,3];
        //   gcd = [0,2,2,3];
        //   prefix_sum = [0,2,4,7];
        //   queries = [0,1,2,3,4]
        // For queries[2] = 2 to find the value for the 3rd gcd pair, use binary search
        // to find the first number that greater than it, which is 4 meaning there is 4
        // pairs with GCD <= 2. Therefore, the index 2 is the answer.
        for (int i = 0; i < m; i++) { // O(m * log(MAX))
            res[i] = searchUpperBound(gcd, queries[i]);
        }
        return res;
    }

    private int searchUpperBound(long[] gcd, long target) {
        int low = 0, high = gcd.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (gcd[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
