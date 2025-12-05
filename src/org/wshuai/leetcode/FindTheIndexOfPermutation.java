package org.wshuai.leetcode;

/**
 * Created by Wei on 11/07/2025.
 * #3109 https://leetcode.com/problems/find-the-index-of-permutation/
 */
public class FindTheIndexOfPermutation {

    private static final int MOD = (int)1e9 + 7;
    private static final int MAX = 100_000;
    private static final long[] FACTORIAL = new long[MAX];

    static {
        FACTORIAL[0] = 1;
        for (int i = 1; i < MAX; i++) {
            FACTORIAL[i] = FACTORIAL[i - 1] * i % MOD;
        }
    }

    // time O(n * log(n)), space O(n)
    public int getPermutationIndex(int[] perm) {
        // #0060 Cantor expansion
        long res = 0;
        int n = perm.length;
        BIT bit = new BIT(n);
        for (int i = 0, j = n - 1; i < n; i++, j--) {
            int k = bit.query(perm[i]);
            res = (res + (k - 1) * FACTORIAL[j] % MOD) % MOD;
            bit.update(perm[i], -1);
        }
        return (int) (res);
    }

    private static class BIT {

        private final int[] tree;

        public BIT(int n) {
            tree = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                tree[i]++;
                int next = i + (i & -i);
                if (next <= n) {
                    tree[next] += tree[i];
                }
            }
        }

        public void update(int index, int val) {
            while (index < tree.length) {
                tree[index] += val;
                index += index & -index;
            }
        }

        public int query(int index) {
            int res = 0;
            while (index > 0) {
                res += tree[index];
                index -= index & -index;
            }
            return res;
        }
    }
}
