package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/11/2019.
 * #0658 https://leetcode.com/problems/find-k-closest-elements/
 */
public class FindKClosestElements {

    // time O(log(n - k) + k), space O(k)
    public List<Integer> findClosestElementsBinarySearch(int[] arr, int k, int x) {
        // https://leetcode.com/problems/find-k-closest-elements/solutions/106426/JavaC++Python-Binary-Search-O(log(N-K)-+-K)/
        // https://www.youtube.com/watch?v=gfwLpRYbCx0
        List<Integer> res = new ArrayList<>();
        int n = arr.length, low = 0, high = n - k;
        while (low < high) {
            int mid = low + (high - low) / 2;
            // mid is too low, need to increase it so x can be
            // in the middle:
            //  |__________|
            // mid       x
            if (x - arr[mid] > arr[mid + k] - x) {
                low = mid + 1;
            } else {
                // mid is too high, need to decrease it so x can be
                // in the middle:
                //    |__________|
                //  mid x
                high = mid;
            }
        }
        for (int a = low; a < low + k; a++) {
            res.add(arr[a]);
        }
        return res;
    }

    // time O(n), space O(1)
    public List<Integer> findClosestElementsTwoPointers(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        int n = arr.length, i = 0, j = n - 1;
        while (i < j - k + 1) {
            if (x - arr[i] > arr[j] - x) {
                i++;
            } else {
                j--;
            }
        }
        for (int a = 0; a < k; a++) {
            res.add(arr[i + a]);
        }
        return res;
    }
}
