package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 01/09/2020.
 * #0060 https://leetcode.com/problems/permutation-sequence/
 */
public class PermutationSequence {

    // time O(n * log(n) * log(n)), space O(n)
    public String getPermutationBinaryIndexedTree(int n, int k) {
        int[] factorial = new int[n + 1];
        StringBuilder sb = new StringBuilder();
        factorial[0] = 1;
        for(int i = 1; i <= n; i++){
            factorial[i] = factorial[i - 1] * i;
        }
        k--;
        BIT bit = new BIT(n);
        for(int i = 1; i <= n; i++){
            int index = k / factorial[n - i];
            int j = bit.search(index + 1);
            sb.append(j);
            bit.update(j, -1);
            k -= index * factorial[n - i];
        }
        return sb.toString();
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

        public int pre(int index) {
            int res = 0;
            while (index > 0) {
                res += tree[index];
                index -= index & -index;
            }
            return res;
        }

        public int search(int target) {
            int low = 1, high = tree.length;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (pre(mid) < target) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }
    }

    // time O(n^2), space O(n)
    public String getPermutation(int n, int k) {
        // Cantor expansion
        // https://leetcode.cn/problems/permutation-sequence/solutions/123593/kang-tuo-bian-ma-by-rayyi-2/
        List<Integer> numbers = new ArrayList<>();
        int[] factorial = new int[n + 1];
        StringBuilder sb = new StringBuilder();
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
            numbers.add(i);
        }
        k--;
        for (int i = 1; i <= n; i++) { // O(n)
            int index = k / factorial[n - i];
            sb.append(numbers.remove(index)); // O(n)
            k -= index * factorial[n - i];
        }
        return sb.toString();
    }
}
