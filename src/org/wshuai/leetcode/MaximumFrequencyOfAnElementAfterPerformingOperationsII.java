package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Wei on 10/30/2025.
 * #3347 https://leetcode.com/problems/maximum-frequency-of-an-element-after-performing-operations-ii/
 */
public class MaximumFrequencyOfAnElementAfterPerformingOperationsII {

    // time O(n * log(n)), space O(n)
    public int maxFrequency(int[] nums, int k, int numOperations) {
        // https://leetcode.cn/problems/maximum-frequency-of-an-element-after-performing-operations-ii/solutions/2983355/liang-chong-fang-fa-chai-fen-hua-dong-ch-7buy/
        int res = 0, count = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> diff = new TreeMap<>();
        for (int num : nums) {
            freq.merge(num, 1, Integer::sum);
            diff.putIfAbsent(num, 0);
            diff.merge(num - k, 1, Integer::sum);
            diff.merge(num + k + 1, -1, Integer::sum);
        }
        for (Map.Entry<Integer, Integer> entry: diff.entrySet()) {
            count += entry.getValue();
            res = Math.max(res, Math.min(count, freq.getOrDefault(entry.getKey(), 0) + numOperations));
        }
        return res;
    }
}
