package org.wshuai.leetcode;

/**
 * Created by Wei on 01/21/2024.
 * #1649 https://leetcode.com/problems/create-sorted-array-through-instructions/
 */
public class CreateSortedArrayThroughInstructions {

    // time O(m * log(n)), space O(n)
    public int createSortedArray(int[] instructions) {
        int res = 0, n = (int) 1e5 + 1, mod = (int) 1e9 + 7;
        int[] bit = new int[n];
        for (int i = 0; i < instructions.length; i++) {
            res = (res + Math.min(query(instructions[i] - 1, bit), i - query(instructions[i], bit))) % mod;
            update(instructions[i], n, bit);
        }
        return res;
    }

    private void update(int index, int n, int[] bit) {
        while (index < n) {
            bit[index]++;
            index += (index & -index);
        }
    }

    private int query(int index, int[] bit) {
        int res = 0;
        while (index > 0) {
            res += bit[index];
            index -= (index & -index);
        }
        return res;
    }

    // time O(m * log(n)), space O(n)
    public int createSortedArraySegmentTree(int[] instructions) {
        int res = 0, n = (int)1e5 + 1, mod = (int)1e9 + 7;
        int[] st = new int[n << 1];
        for (int i = 0; i < instructions.length; i++) {
            res = (res +  Math.min(query(1, instructions[i], st, n), query(instructions[i] + 1, n, st, n))) % mod;
            update(instructions[i], 1, st, n);
        }
        return res;
    }

    private void update(int index, int value, int[] st, int n) {
        index += n;
        st[index] += value;
        for (index >>= 1; index > 0; index >>= 1) {
            st[index] = st[index << 1] + st[(index << 1) + 1];
        }
    }

    private int query(int left, int right, int[] st, int n) {
        int res = 0;
        for (left += n, right += n; left < right; left >>= 1, right >>= 1) {
            if ((left & 1) == 1) {
                res += st[left++];
            }
            if ((right & 1) == 1) {
                res += st[--right];
            }
        }
        return res;
    }
}
