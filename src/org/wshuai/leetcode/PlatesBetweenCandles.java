package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/01/2023.
 * #2055 https://leetcode.com/problems/plates-between-candles/description/
 */
public class PlatesBetweenCandles {

    // time O(m + n), space O(m + n)
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int[] res = new int[queries.length], prefixSum = new int[s.length() + 1],
                prev = new int[s.length() + 1], next = new int[s.length() + 1];
        Arrays.fill(next, Integer.MAX_VALUE);
        for (int i = 0; i < s.length(); i++) {
            prefixSum[i + 1] = prefixSum[i] + (s.charAt(i) == '|' ? 1 : 0);
            prev[i + 1] = s.charAt(i) == '|' ? i : prev[i];
        }
        for (int j = s.length() - 1; j >= 0; j--) {
            next[j] = s.charAt(j) == '|' ? j : next[j + 1];
        }
        int index = 0;
        for (int[] query : queries) {
            int left = next[query[0]], right = prev[query[1] + 1];
            res[index++] = left < right ? right - left + 1 - (prefixSum[right + 1] - prefixSum[left]) : 0;
        }
        return res;
    }

    // time O(m*log(n) + n), space O(m + n)
    public int[] platesBetweenCandlesBinarySearch(String s, int[][] queries) {
        int[] res = new int[queries.length], prefixSum = new int[s.length() + 1];
        int candles = 0, index = 0, current = 0;
        for (int i = 0; i < s.length(); i++) {
            prefixSum[i + 1] = prefixSum[i];
            if (s.charAt(i) == '|') {
                prefixSum[i + 1]++;
                candles++;
            }
        }
        int[] candleIndex = new int[candles];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '|') {
                candleIndex[index++] = i;
            }
        }
        for (int[] query: queries) {
            int left = binarySearchCeiling(candleIndex, query[0]), right = binarySearchFloor(candleIndex, query[1]);
            res[current++] = left != -1 && right != -1 && left < right ? right - left + 1 - (prefixSum[right + 1] - prefixSum[left]) : 0;
        }
        return res;
    }

    private int binarySearchCeiling(int[] nums, int num) {
        int res = -1, left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == num) {
                return num;
            } else if (nums[mid] > num) {
                res = nums[mid];
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    private int binarySearchFloor(int[] nums, int num) {
        int res = -1, left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == num) {
                return num;
            } else if (nums[mid] > num) {
                right = mid - 1;
            } else {
                res = nums[mid];
                left = mid + 1;
            }
        }
        return res;
    }
}
