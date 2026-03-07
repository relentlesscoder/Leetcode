package org.wshuai.leetcode.other;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 12/30/2025.
 * #2031 https://leetcode.cn/problems/count-subarrays-with-more-ones-than-zeros/
 */
public class CountSubarraysWithMoreOnesThanZeros {

    private static final int MOD = (int) 1e9 + 7;

    // time O(n), space O(n)
    public int subarraysWithMoreOnesThanZeroesPrefixSumArray(int[] nums) {
        // #3739原题
        long res = 0;
        int n = nums.length;
        int[] cnt = new int[(n << 1) + 1];
        cnt[n]++;
        for (int i = 0, sum = 0, count = 0; i < n; i++) {
            if (nums[i] == 1) {
                count += cnt[sum + n];
                sum++;
            } else {
                sum--;
                count -= cnt[sum + n];
            }
            res += count;
            cnt[sum + n]++;
        }
        return (int) (res % MOD);
    }

    // time O(n), space O(n)
    public int subarraysWithMoreOnesThanZeroesPrefixSumHashMap(int[] nums) {
        long res = 0;
        int n = nums.length;
        Map<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, 1);
        for (int i = 0, sum = 0, count = 0; i < n; i++) {
            if (nums[i] == 1) {
                count += cnt.getOrDefault(sum, 0);
                sum++;
            } else {
                sum--;
                count -= cnt.getOrDefault(sum, 0);
            }
            res += count;
            cnt.merge(sum, 1, Integer::sum);
        }
        return (int) (res % MOD);
    }
}
