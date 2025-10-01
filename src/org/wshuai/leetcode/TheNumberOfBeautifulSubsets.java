package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/01/2025.
 * #2597 https://leetcode.com/problems/the-number-of-beautiful-subsets/
 */
public class TheNumberOfBeautifulSubsets {

    // time O(n * 2^n), space O(n)
    public int beautifulSubsets(int[] nums, int k) {
        return dfs(nums, k, 0, 0);
    }

    private int dfs(int[] nums, int k, int index, int mask) {
        if (index == nums.length) {
            return mask == 0 ? 0 : 1;
        }
        boolean isBeautiful = true;
        for (int j = 0; j < index && isBeautiful; j++) {
            isBeautiful = ((1 << j) & mask) == 0 || Math.abs(nums[j] - nums[index]) != k;
        }
        int skip = dfs(nums, k, index + 1, mask);
        int take = isBeautiful ? dfs(nums, k, index + 1, mask + (1 << index)) : 0;
        return skip + take;
    }

    // time O(2^n), space O(n)
    public int beautifulSubsetsBitMask(int[] nums, int k) {
        int res = 0, n = nums.length, mask = 1 << n;
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) == k) {
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }
        for (int i = 1; i < mask; i++) {
            if (isBeautiful(i, nums, k, adj)) {
                res++;
            }
        }
        return res;
    }

    private boolean isBeautiful(int mask, int[] nums, int k, List<Integer>[] adj) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (((1 << i) & mask) == 0) {
                continue;
            }
            for (int j = 0; j < adj[i].size(); j++) {
                if (((1 << adj[i].get(j)) & mask) != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
