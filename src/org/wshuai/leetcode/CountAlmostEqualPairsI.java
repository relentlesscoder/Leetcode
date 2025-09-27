package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 09/26/2025.
 * #3265 https://leetcode.com/problems/count-almost-equal-pairs-i/
 */
public class CountAlmostEqualPairsI {

    private static final int[] POW10 = {1, 10, 100, 1000, 10000, 100000, 1000000};

    // time O(n * log(n) + n * m^2), space O(n + m^2)
    public int countPairsMath(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        int[] digits = new int[7];
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            Set<Integer> unique = new HashSet<>();
            unique.add(num);
            int m = 0;
            for (int val = num; val > 0; val /= 10) {
                digits[m++] = val % 10;
            }
            for (int i = 0; i < m; i++) {
                for (int j = i + 1; j < m; j++) {
                    if (digits[i] == digits[j]) {
                        continue;
                    }
                    // exchange i and j, the delta is
                    // (a[j] - a[i]) * 10^i + (a[i] - a[j]) * 10^j
                    // = (a[j] - a[i]) * (10^i - 10^j)
                    unique.add(num + (digits[j] - digits[i]) * (POW10[i] - POW10[j]));
                }
            }
            for (int val : unique) {
                res += freq.getOrDefault(val, 0);
            }
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        return res;
    }

    // time O(n * log(n) + n * m^3), space O(n + m^2)
    public int countPairs(int[] nums) {
        // sort to avoid the case like [10,1]
        Arrays.sort(nums);
        int res = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            // use set to store unique values for number like 111
            Set<Integer> unique = new HashSet<>();
            unique.add(num);
            char[] arr = Integer.toString(num).toCharArray();
            for (int i = 0; i < arr.length - 1; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[i] == arr[j]) {
                        continue;
                    }
                    swap(arr, i, j); // swap i and j
                    unique.add(Integer.parseInt(String.valueOf(arr)));
                    swap(arr, i, j); // reset
                }
            }
            for (int val : unique) {
                res += freq.getOrDefault(val, 0);
            }
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        return res;
    }

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
