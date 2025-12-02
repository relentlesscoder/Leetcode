package org.wshuai.leetcode;

/**
 * Created by Wei on 12/02/2025.
 * #3134 https://leetcode.com/problems/find-the-median-of-the-uniqueness-array/
 */
public class FindTheMedianOfTheUniquenessArray {

    // time O(n * log(D)), space O(MAX)
    public int medianOfUniquenessArray(int[] nums) {
        int n = nums.length, low = 1, high = 0, max = 0;
        long total = (long) n * (n + 1) / 2, half = (total + 1) / 2;
        int[] freq = new int[100_001];
        for (int num : nums) {
            max = Math.max(max, num);
            if (freq[num]++ == 0) {
                high++;
            }
        }
        while (low < high) { // log(D)
            int mid = low + (high - low) / 2;
            // Find number of subarrays that has at most k distinct numbers
            long sum = countUniquenessArray(nums, mid, max); // O(n)
            if (sum < half) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private long countUniquenessArray(int[] nums, int k, int max) {
        long res = 0;
        int n = nums.length;
        int[] freq = new int[max + 1];
        for (int i = 0, j = 0, distinct = 0; i < n; i++) {
            if (freq[nums[i]]++ == 0) {
                distinct++;
            }
            while (distinct > k) {
                if (--freq[nums[j++]] == 0) {
                    distinct--;
                }
            }
            res += i - j + 1;
        }
        return res;
    }
}
