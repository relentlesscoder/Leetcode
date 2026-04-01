package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 11/13/2025.
 * #2547 https://leetcode.com/problems/minimum-cost-to-split-an-array/
 */
public class MinimumCostToSplitAnArray {

    // 三种解法的共同核心思路:
    // 划分型 DP: 将数组划分成若干子数组, 每段的 cost = k + trimmedLength
    // trimmedLength = 去掉只出现一次的元素后的长度 = 段长度 - distinct (只出现一次的元素个数)
    // 即 cost = k + count - distinct, 其中 count = 段长度, distinct = 段内只出现一次的元素个数
    //
    // O(n^2) 解法: 枚举最后一段起点 j, 边扫边维护 freq/count/distinct
    // O(n log n) 解法: 用线段树优化, 将 cost 拆解后用区间更新维护

    // time O(n * log(n)), space O(n)
    public int minCostSegmentTree(int[] nums, int k) {
        // 线段树优化: 把 "枚举 j 取 min" 转化为线段树区间最小值查询
        // 将 cost = k + count - distinct 变形:
        // cost(j, i) = k + (i-j+1) - distinct(j, i)
        // dp[i] = min over j { dp[j-1] + k + (i-j+1) - distinct(j, i) }
        // = k + i + 1 + min over j { dp[j-1] - j - distinct(j, i) }
        //
        // 线段树维护: tree[j] = dp[j-1] - j + "当前 distinct 的调整值"
        // 每加入 nums[i], 通过区间更新调整 distinct 的变化:
        // - nums[i] 首次出现在某些段中 → distinct +1 → tree 值 -1
        // - nums[i] 第二次出现在某些段中 → distinct -1 → tree 值 +1
        int n = nums.length, res = 0;
        SegmentTree st = new SegmentTree(n);
        // last[num] = num 上一次出现的位置 (1-indexed), 0 表示未出现
        // prevToLast[num] = num 上上次出现的位置
        int[] last = new int[n], prevToLast = new int[n];
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            // 将 dp[i-1] - i 写入位置 i (对应段起点 j = i, 即 nums[i] 单独一段)
            st.update(i, i, res);
            // nums[i] 加入后, 对于起点 j in [last[num]+1, i], nums[i] 是首次出现
            // distinct +1 → tree 值 -1
            st.update(last[num] + 1, i, -1);
            if (last[num] > 0) {
                // 对于起点 j in [prevToLast[num]+1, last[num]]:
                // 之前 nums[num] 在 last[num] 处只出现一次 (被算为 distinct)
                // 现在 nums[i] 加入后变成出现两次, distinct -1 → tree 值 +1
                st.update(prevToLast[num] + 1, last[num], 1);
            }
            // 查询所有可能的起点 j 的最小值
            res = k + st.query(1, i);
            prevToLast[num] = last[num];
            last[num] = i;
        }
        // 最后加上 n 是因为变形时把 (i-j+1) 中的常数项提出来了
        return res + n;
    }

    private static class SegmentTree {

        private final int n;
        private final int[] tree;
        private final int[] mark;

        public SegmentTree(int n) {
            this.n = n;
            int size = 2 << (32 - Integer.numberOfLeadingZeros(n));
            tree = new int[size];
            mark = new int[size];
        }

        public int query(int start, int end) {
            return query(1, 1, n, start, end);
        }

        public void update(int start, int end, int val) {
            update(1, 1, n, start, end, val);
        }

        private void update(int node, int left, int right, int start, int end, int val) {
            if (left >= start && right <= end) {
                apply(node, val);
                return;
            }
            spread(node);
            int mid = (left + right) / 2;
            if (start <= mid) {
                update(node * 2, left, mid, start, end, val);
            }
            if (end > mid) {
                update(node * 2 + 1, mid + 1, right, start, end, val);
            }
            maintain(node);
        }

        private int query(int node, int left, int right, int start, int end) {
            if (left >= start && right <= end) {
                return tree[node];
            }
            spread(node);
            int mid = (left + right) / 2;
            if (end <= mid) {
                return query(node * 2, left, mid, start, end);
            }
            if (start > mid) {
                return query(node * 2 + 1, mid + 1, right, start, end);
            }
            int lr = query(node * 2, left, mid, start, end);
            int rr = query(node * 2 + 1, mid + 1, right, start, end);
            return merge(lr, rr);
        }

        private void apply(int node, int val) {
            tree[node] += val;
            mark[node] += val;
        }

        private void spread(int node) {
            if (mark[node] == 0) {
                return;
            }
            apply(node * 2, mark[node]);
            apply(node * 2 + 1, mark[node]);
            mark[node] = 0;
        }

        private void maintain(int node) {
            tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
        }

        private int merge(int v1, int v2) {
            return Math.min(v1, v2);
        }
    }

    // time O(n^2), space O(n)
    public int minCostDP(int[] nums, int k) {
        // dp[i+1] = nums[0..i] 的最小总 cost
        int n = nums.length, mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }
        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            // freq[v] = nums[v] 在 nums[j..i] 中出现的次数
            int[] freq = new int[mx + 1];
            int res = Integer.MAX_VALUE;
            // 从 i 往左扫, 枚举最后一段起点 j
            // count = 段长度, distinct = 段内只出现一次的元素个数
            for (int j = i, count = 0, distinct = 0; j >= 0; j--) {
                count++;
                if (freq[nums[j]] == 0) {
                    distinct += 1; // 新出现的元素, 暂时算作 distinct
                }
                if (freq[nums[j]] == 1) {
                    distinct -= 1; // 出现第二次, 不再是 distinct
                }
                freq[nums[j]]++;
                // cost(j, i) = k + trimmedLength = k + count - distinct
                res = Math.min(res, k + count - distinct + dp[j]);
            }
            dp[i + 1] = res;
        }
        return dp[n];
    }

    // time O(n^2), space O(n + max(nums))
    public int minCostDFSWithMemorization(int[] nums, int k) {
        // dfs(i) = nums[0..i] 的最小总 cost
        int n = nums.length, mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return dfs(n - 1, nums, k, mx, memo);
    }

    // dfs(i) = nums[0..i] 的最小总 cost
    private int dfs(int i, int[] nums, int k, int max, int[] memo) {
        if (i == -1) {
            return 0; // 所有元素都处理完了
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        int[] freq = new int[max + 1];
        int res = Integer.MAX_VALUE;
        // 枚举最后一段起点 j, 边扫边维护 count/distinct
        for (int j = i, count = 0, distinct = 0; j >= 0; j--) {
            count++;
            if (freq[nums[j]] == 0) {
                distinct += 1; // 首次出现
            }
            if (freq[nums[j]] == 1) {
                distinct -= 1; // 第二次出现, 不再 distinct
            }
            freq[nums[j]]++;
            // cost = k + count - distinct, 递归处理 nums[0..j-1]
            res = Math.min(res, k + count - distinct + dfs(j - 1, nums, k, max, memo));
        }
        return memo[i] = res;
    }
}
