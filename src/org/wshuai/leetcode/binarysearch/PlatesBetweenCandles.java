package org.wshuai.leetcode.binarysearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 09/01/2023.
 * #2055 https://leetcode.com/problems/plates-between-candles/description/
 */
public class PlatesBetweenCandles {

    // time O(m + n), space O(m + n)
    public int[] platesBetweenCandles(String s, int[][] queries) {
        // 用三个数组分别存蜡烛数量的前缀和，当前索引的上一个蜡烛的位置和下一个蜡烛
        // 的位置。遍历询问，对每一个询问求出蜡烛和盘子的总数减去蜡烛的数量:
        //   r - l + 1 - (prefix[r + 1] - prefix[l])
        int n = s.length(), m = queries.length;
        int[] res = new int[m],
                prefix = new int[n + 1], // 蜡烛前缀和
                last = new int[n + 1], // 上一个蜡烛的位置
                next = new int[n + 1]; // 下一个蜡烛的位置
        last[0] = -1;
        next[n] = n;
        // 一次遍历计算前缀和和上一个蜡烛的位置
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            prefix[i + 1] = prefix[i] + (c == '|' ? 1 : 0);
            last[i + 1] = c == '|' ? i : last[i];
        }
        // 逆序遍历求下一个蜡烛的位置
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            next[i] = c == '|' ? i : next[i + 1];
        }
        for (int i = 0; i < m; i++) {
            int[] q = queries[i];
            int l = next[q[0]], r = last[q[1] + 1];
            if (l >= r) {
                continue;
            }
            // 计算所有蜡烛之间盘子的数量
            res[i] = r - l + 1 - prefix[r + 1] + prefix[l];
        }
        return res;
    }

    // time O(m * log(n) + n), space O(m + n)
    public int[] platesBetweenCandlesBinarySearch(String s, int[][] queries) {
        // 新建一个数组 nums 存所有蜡烛在字符串中的索引，对每个询问查找在范围内的第一个和
        // 最后一个蜡烛。询问的答案即为蜡烛和盘子的总数减去蜡烛的数量:
        //   nums.get(r) - nums.get(l) - (r - l)
        int n = s.length(), m = queries.length;
        int[] res = new int[m];
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) { // O(n)
            if (s.charAt(i) == '|') {
                nums.add(i);
            }
        }
        if (nums.isEmpty()) {
            return res;
        }
        for (int i = 0; i < m; i++) { // O(m)
            int[] q = queries[i];
            int l = lowerBound(nums, q[0]), r = higherBound(nums, q[1]); // O(n)
            if (l == -1 || r == -1 || l >= r) {
                continue;
            }
            res[i] = nums.get(r) - nums.get(l) - (r - l);
        }
        return res;
    }

    private int higherBound(List<Integer> nums, int target) {
        int low = 0, high = nums.size() - 1;
        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            if (nums.get(mid) > target) {
                high = mid - 1;
            } else {
                low = mid;
            }
        }
        return nums.get(low) <= target ? low : -1;
    }

    private int lowerBound(List<Integer> nums, int target) {
        int low = 0, high = nums.size() - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return nums.get(low) >= target ? low : -1;
    }
}
