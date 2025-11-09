package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/09/2025.
 * #3636 https://leetcode.com/problems/threshold-majority-queries/
 */
public class ThresholdMajorityQueries {

    private final Map<Integer, Integer> freq = new HashMap<>();

    private int[] nums;
    private int[] indexToValue;
    private int[] freqArray;

    private int maxCount = 0;
    private int minVal = 0;

    // time O(n * sqrt(m) + m * log(m) + n * log(n)), space O(n + m)
    public int[] subarrayMajorityDiscretization(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        this.nums = nums;
        freqArray = new int[n + 1];
        indexToValue = new int[n];

        int[] sorted = Arrays.stream(nums).sorted().toArray();
        for (int i = 0; i < n; i++) {
            indexToValue[i] = binarySearch(sorted, nums[i]);
        }

        int[] res = new int[m];
        int blockSize = (int) Math.ceil(n / Math.sqrt(m * 2));

        record Query(int blockId, int left, int right, int threshold, int queryId) {}

        List<Query> qs = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int[] q = queries[i];
            int l = q[0], r = q[1] + 1, threshold = q[2];

            if (r - l > blockSize) {
                qs.add(new Query(l / blockSize, l, r, threshold, i));
                continue;
            }

            for (int j = l; j < r; j++) {
                addIndex(j);
            }
            res[i] = maxCount >= threshold ? minVal : -1;
            for (int j = l; j < r; j++) {
                freqArray[indexToValue[j]]--;
            }
            maxCount = 0;
        }

        qs.sort((a, b) -> a.blockId != b.blockId ? a.blockId - b.blockId : a.right - b.right);

        int rightEnd = 0;
        for (int i = 0; i < qs.size(); i++) {
            Query curr = qs.get(i);
            int blockEnd = (curr.blockId + 1) * blockSize;
            if (i == 0 || curr.blockId > qs.get(i - 1).blockId) { // New block
                rightEnd = blockEnd; // Start position for right end
                // Reset data
                Arrays.fill(freqArray, 0);
                maxCount = 0;
            }

            // Move right end from start to q.Right (exclusive)
            for (; rightEnd < curr.right; rightEnd++) {
                addIndex(rightEnd);
            }

            int tempMaxCount = maxCount;
            int tempMinVal = minVal;

            // Move left end from q.left to blockEnd (exclusive)
            for (int j = curr.left; j < blockEnd; j++) {
                addIndex(j);
            }
            res[curr.queryId] = maxCount >= curr.threshold ? minVal : -1;

            // Rollback
            maxCount = tempMaxCount;
            minVal = tempMinVal;
            for (int j = curr.left; j < blockEnd; j++) {
                freqArray[indexToValue[j]]--;
            }
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

    private void addIndex(int idx) {
        int val = indexToValue[idx];
        int count = ++freqArray[val];
        int num = nums[idx];
        if (count > maxCount) {
            maxCount = count;
            minVal = num;
        } else if (count == maxCount) {
            minVal = Math.min(minVal, num);
        }
    }

    // time O(n * sqrt(m) + m * log(m)), space O(n + m)
    public int[] subarrayMajority(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        int[] res = new int[m];
        int blockSize = (int) Math.ceil(n / Math.sqrt(m * 2)); // ceil(n/sqrt(2 * m))

        record Query(int blockId, int left, int right, int threshold, int queryId) {}

        List<Query> qs = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int[] q = queries[i];
            int l = q[0], r = q[1] + 1, threshold = q[2];

            if (r - l > blockSize) {
                qs.add(new Query(l / blockSize, l, r, threshold, i));
                continue;
            }

            for (int j = l; j < r; j++) {
                add(nums[j]);
            }
            res[i] = maxCount >= threshold ? minVal : -1;
            freq.clear();
            maxCount = 0;
        }

        qs.sort((a, b) -> a.blockId != b.blockId ? a.blockId - b.blockId : a.right - b.right); // O(m * log(m))

        int rightEnd = 0;
        for (int i = 0; i < qs.size(); i++) { // total O(n * sqrt(m))
            Query curr = qs.get(i);
            int blockEnd = (curr.blockId + 1) * blockSize;
            if (i == 0 || curr.blockId > qs.get(i - 1).blockId) { // New block
                rightEnd = blockEnd; // Start position for right end
                // Reset data
                freq.clear();
                maxCount = 0;
            }

            // Move right end from start to q.Right (exclusive)
            for (; rightEnd < curr.right; rightEnd++) {
                add(nums[rightEnd]);
            }

            int tempMaxCount = maxCount;
            int tempMinVal = minVal;

            // Move left end from q.left to blockEnd (exclusive)
            for (int j = curr.left; j < blockEnd; j++) {
                add(nums[j]);
            }
            res[curr.queryId] = maxCount >= curr.threshold ? minVal : -1;

            // Rollback
            maxCount = tempMaxCount;
            minVal = tempMinVal;
            for (int j = curr.left; j < blockEnd; j++) {
                freq.merge(nums[j], -1, Integer::sum);
            }
        }
        return res;
    }

    private void add(int num) {
        int count = freq.merge(num, 1, Integer::sum);
        if (count > maxCount) {
            maxCount = count;
            minVal = num;
        } else if (count == maxCount) {
            minVal = Math.min(minVal, num);
        }
    }
}
