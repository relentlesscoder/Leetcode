package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/09/2025.
 * #2818 https://leetcode.com/problems/apply-operations-to-maximize-score/
 */
public class ApplyOperationsToMaximizeScore {

    private static final int MOD = (int)1e9 + 7;
    private static final int MAX = (int)1e5 + 1;
    private static final int[] PRIME_SCORE = new int[MAX];

    static {
        for (int i = 2; i < MAX; i++) {
            // If PRIME_SCORE[i] already set then current i is not a prime number
            if (PRIME_SCORE[i] == 0) {
                for (int j = i; j < MAX; j += i) {
                    PRIME_SCORE[j]++;
                }
            }
        }
    }

    // time O(n * log(n)), space O(n)
    public int maximumScoreOptimizedWithPreprocessingPrimeFactor(List<Integer> nums, int k) {
        long res = 1L;
        int n = nums.size();
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);
        Deque<Integer> queue = new ArrayDeque<>();
        queue.push(-1);
        for (int i = 0; i < n; i++) {
            // right[i] is the first element on nums[i]'s right with score(nums[queue.peek()]) > score(nums[i])
            // Note that the requirement is to find the smallest index when multiple numbers has the same score
            // so subarrays containing elements in nums[i]'s right with same score is valid.
            while (queue.size() > 1 && PRIME_SCORE[nums.get(queue.peek())] < PRIME_SCORE[nums.get(i)]) {
                right[queue.pop()] = i;
            }
            // left[i] is the first element on nums[i]'s left with score(nums[queue.peek()]) >= score(nums[i])
            // Note that the requirement is to find the smallest index when multiple numbers has the same score
            // so valid subarrays can't include elements in nums[i]'s left with same score.
            left[i] = queue.peek();
            queue.push(i);
        }
        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, index -> index);
        Arrays.sort(ids, (i, j) -> nums.get(j) - nums.get(i));
        for (int i = 0; i < n; i++) {
            int l = ids[i] - left[ids[i]], r = right[ids[i]] - ids[i];
            long count = 1L * l * r;
            if (count >= k) {
                res = res * pow(nums.get(ids[i]), k) % MOD;
                break;
            }
            res = res * pow(nums.get(ids[i]), (int) count) % MOD;
            k -= count;
        }
        return (int) res;
    }

    // time O(n * sqrt(max) + n * log(n)), space O(n)
    public int maximumScore(List<Integer> nums, int k) {
        long res = 1L;
        int n = nums.size();
        int[] score = new int[n]; // score[i] stores prime score of nums[i]
        int[] left = new int[n]; // left[i] stores the index of last left number with score(nums[left[i]]) >= score(nums[i])
        int[] right = new int[n]; // right[i] stores the index of next right number with score(nums[right[i]]) > score(nums[i])
        for (int i = 0; i < n; i++) {
            score[i] = getPrimeScore(nums.get(i)); // Calculate prime score for each number
        }
        // Use monotonic queue to calculate left[i] and right[i] for each number
        Deque<Integer> queue = new ArrayDeque<>();
        queue.push(-1); // Left sentinel
        for (int i = 0; i < n; i++) {
            while (queue.size() > 1 && score[queue.peek()] < score[i]) {
                queue.pop();
            }
            left[i] = queue.peek();
            queue.push(i);
        }
        queue.clear();
        queue.push(n); // Right sentinel
        for (int i = n - 1; i >= 0; i--) {
            while (queue.size() > 1 && score[queue.peek()] <= score[i]) {
                queue.pop();
            }
            right[i] = queue.peek();
            queue.push(i);
        }
        // Sort the indexes in descending order since we would like to process from the largest number
        // to least to maximize the score
        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, index -> index);
        Arrays.sort(ids, (i, j) -> nums.get(j) - nums.get(i));
        for (int i = 0; i < n; i++) {
            // Valid subarrays that has nums[i] as the number with max prime score
            // can start with (left[i], i] and end with [i, right[i])
            int l = ids[i] - left[ids[i]], r = right[ids[i]] - ids[i];
            // Total number of such subarrays is (i - left[i]) * (right[i] - i)
            // for example: [0,1,2,3,4,5,6,7,8,9]
            // for index 5, left[5] = 1, right[5] = 8 then count = 12
            // [2,3,4,5]
            // [2,3,4,5,6]
            // [2,3,4,5,6,7]
            // [3,4,5]
            // [3,4,5,6]
            // [3,4,5,6,7]
            // [4,5]
            // [4,5,6]
            // [4,5,6,7]
            // [5]
            // [5,6]
            // [5,6,7]
            long count = 1L * l * r;
            // Each nums[i] contributes pow(nums[i], Math.min(k, count)) to the final score
            if (count >= k) {
                res = res * pow(nums.get(ids[i]), k) % MOD;
                break;
            }
            res = res * pow(nums.get(ids[i]), (int) count) % MOD;
            k -= count;
        }
        return (int) res;
    }

    private long pow(long num, int n) {
        long res = 1L;
        while (n > 0) {
            if (n % 2 == 1) {
                res = res * num % MOD;
            }
            num = num * num % MOD;
            n >>= 1;
        }
        return res;
    }

    private int getPrimeScore(int n) {
        Set<Integer> primeFactors = new HashSet<>();
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                primeFactors.add(i);
                while (n % i == 0) {
                    n /= i;
                }
            }
        }
        if (n > 1) {
            primeFactors.add(n);
        }
        return primeFactors.size();
    }
}
