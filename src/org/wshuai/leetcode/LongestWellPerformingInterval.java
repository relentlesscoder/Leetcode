package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/25/2019.
 * #1124 https://leetcode.com/problems/longest-well-performing-interval/
 */
public class LongestWellPerformingInterval {

    private static final int THRESHOLD = 8;

    public int longestWPIMonotonicStack(int[] hours) {
		// 题解：https://leetcode.cn/problems/longest-well-performing-interval/solutions/2110211/liang-chong-zuo-fa-liang-zhang-tu-miao-d-hysl/
		// 利用前缀和 s，将问题变为：找到两个下标 i 和 j，满足 j < i 且
		// s[j] < s[i]，最大化 i − j 的值。
		// 示例1:
        // Input:      1,  1, -1, -1, -1, -1,  1
        // Prefix:  0, 1,  2,  1,  0, -1, -2, -1
        // Stack:   0, x,  x,  x,  x,  5,  6,  x
        int res = 0, n = hours.length;
        int[] prefix = new int[n + 1];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + (hours[i - 1] > THRESHOLD ? 1 : -1);
            if (prefix[i] < prefix[stack.peek()]) {
                stack.push(i);
            }
        }
        for (int i = n; i > 0; i--) {
            while (!stack.isEmpty() && prefix[i] > prefix[stack.peek()]) {
                res = Math.max(res, i - stack.pop());
            }
        }
        return res;
    }

    // time O(n), space O(n)
    public int longestWPIPrefixSumHashMap(int[] hours) {
        // 题解：https://leetcode.cn/problems/longest-well-performing-interval/solutions/2110211/liang-chong-zuo-fa-liang-zhang-tu-miao-d-hysl/
        // 利用前缀和 s，将问题变为：找到两个下标 i 和 j，满足 j < i 且
        // s[j] < s[i]，最大化 i − j 的值。
        int res = 0, n = hours.length;
        Map<Integer, Integer> prefix = new HashMap<>();
        for (int i = 0, sum = 0; i < n; i++) {
            sum += hours[i] > THRESHOLD ? 1 : -1;
            // 如果前缀和大于0，则整个前缀数组(-1就是最小的满足上述条件的索引j)
            // 符合要求而且肯定是到目前为止的最大答案。
            if (sum > 0) {
                res = i + 1;
            } else {
                // 如果 s[i] ≤ 0，那么 j 就是 s[i] − 1 首次出现的位置。为什
                // 么是 s[i] − 1 而不是其它更小的数？这是因为前缀和是从 0 开
                // 始的，由于 nums 中只有 1 和 −1，那么相邻前缀和的差都恰好为
                // 1，要想算出比 s[i] − 1 更小的数，必然会先算出 s[i] − 1，
                // 那么这些更小数必然在 s[i] − 1 首次出现的位置的右边。
                // 示例1:  [1, 1,-1,-1,-1,-1, 1, 1,-1,-1]
                //        [1, 2, 1, 0,-1,-2,-1, 0,-1,-2]
                // 子数组 [6,8] 是一个合法子数组
                if (prefix.containsKey(sum - 1)) {
                    res = Math.max(res, i - prefix.get(sum - 1));
                }
                // 越早的索引越有可能组成更长的子数组。注意我们只需要处理非正的
                // 前缀和因为当前缀和大于0整个前缀子数组就是最优解。
                prefix.putIfAbsent(sum, i);
            }
        }
        return res;
    }

    // time O(n), space O(n)
    public int longestWPIPrefixSumArray(int[] hours) {
        // 因为上解中 sum 取值范围为 [0, -n]，我们可以用数组取代哈希表来优化。
        int res = 0, n = hours.length;
        int[] prefix = new int[n + 2];
        Arrays.fill(prefix, n);
        for (int i = 0, sum = 0; i < n; i++) {
            sum += hours[i] > THRESHOLD ? 1 : -1;
            if (sum > 0) {
                res = i + 1;
            } else if (prefix[sum + n] != n) {
                res = Math.max(res, i - prefix[sum + n]);
            }
            // 越早的索引越有可能组成更长的子数组。注意我们只需要处理非正的
            // 前缀和因为当前缀和大于0整个前缀子数组就是最优解。
            if (sum <= 0 && prefix[sum + n + 1] == n) {
                prefix[sum + n + 1] = i;
            }
        }
        return res;
    }
}
