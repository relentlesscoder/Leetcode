package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 11/09/2025.
 * #3590 https://leetcode.com/problems/kth-smallest-path-xor-sum/
 */
public class KthSmallestPathXORSum {

    public int[] kthSmallest(int[] par, int[] vals, int[][] queries) {
        int n = vals.length;
        List<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int v = 1; v < n; v++) { // Build the graph
            adj[par[v]].add(v);
        }

        int[] sum = new int[n]; // Path XOR sum
        int[] in = new int[n]; // DFS in timestamp
        int[] out = new int[n]; // DFS out timestamp
        int[] clock = {0}; // Clock
        dfs(0, 0, sum, in, out, clock, adj, vals); // DFS to calculate path XOR sum and in/out timestamp

        // Discretization on path XOR sum
        int[] sorted = Arrays.stream(sum).sorted().toArray();
        int[] orderMap = new int[n];
        for (int i = 0; i < n; i++) {
            // sum[i] is the path XOR sum for node with DFS in timestamp i
            // so indexMap[i] stores the order for node with DFS in
            // timestamp i in sorted path XOR sum array
            orderMap[i] = binarySearch(sorted, sum[i]) + 1;
        }

        int m = queries.length;
        int blockSize = (int) Math.ceil(n / Math.sqrt(m * 2)); // Block size = ceil(n / sqrt(2 * m));

        record Query(int blockId, int left, int right, int k, int queryId) {}
        Query[] qs = new Query[m];
        for (int i = 0; i < m; i++) {
            int[] q = queries[i];
            // If timstamp is in range [l, r] for q[0], it's on the subtree rooted at q[0]
            int l = in[q[0]], r = out[q[0]];
            qs[i] = new Query(l / blockSize, l, r, q[1], i);
        }

        Arrays.sort(qs, (x, y) -> {
            if (x.blockId != y.blockId) {
                return x.blockId - y.blockId;
            }
            // Even/odd group optimization
            return x.blockId % 2 == 0 ? x.right - y.right : y.right - x.right;
        });

        int[] freq = new int[n + 1];
        BIT bit = new BIT(n + 1);

        int[] res = new int[m];
        int l = 0, r = 0;
        for (Query q : qs) {
            // Query for kth path XOR sum in timestamp range [l, r]
            int ql = q.left, qr = q.right, k = q.k, i = q.queryId;
            while (l < ql) move(orderMap[l++], -1, freq, bit); // l is on left of ql, move l to ql and remove sum[l]
            while (l > ql) move(orderMap[--l],  1, freq, bit); // l is on right of ql, move l to ql and add sum[l]
            while (r < qr) move(orderMap[r++],  1, freq, bit); // r is on left of qr, move l to qr and add sum[r]
            while (r > qr) move(orderMap[--r], -1, freq, bit); // r is on right of qr, move r to qr and remove sum[r]

            int ans = bit.kth(k);
            res[i] = ans <= n ? sorted[ans - 1] : -1;
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

    private void move(int v, int delta, int[] cnt, BIT bit) {
        if (delta > 0) {
            if (cnt[v] == 0) {
                bit.update(v, 1);
            }
            cnt[v]++;
        } else {
            cnt[v]--;
            if (cnt[v] == 0) {
                bit.update(v, -1);
            }
        }
    }

    private void dfs(int node, int xorVal, int[] sum, int[] in, int[] out, int[] clock, List<Integer>[] adj, int[] vals) {
        in[node] = clock[0];
        xorVal ^= vals[node];
        sum[clock[0]++] = xorVal;
        for (int next : adj[node]) {
            dfs(next, xorVal, sum, in, out, clock, adj, vals);
        }
        out[node] = clock[0];
    }

    private static class BIT {

        private final int[] tree;
        private final int hb;

        public BIT(int n) {
            tree = new int[n + 1];
            hb = Integer.highestOneBit(n);
        }

        public void update(int index, int val) {
            while (index < tree.length) {
                tree[index] += val;
                index += index & -index;
            }
        }

        public int kth(int k) {
            int res = 0;
            for (int b = hb; b > 0; b >>= 1) {
                int next = res | b;
                if (next < tree.length && k > tree[next]) {
                    k -= tree[next];
                    res = next;
                }
            }
            return res + 1;
        }
    }
}
