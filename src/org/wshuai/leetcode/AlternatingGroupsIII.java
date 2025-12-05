package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by Wei on 11/07/2025.
 * #3245 https://leetcode.com/problems/alternating-groups-iii/
 */
public class AlternatingGroupsIII {

    public List<Integer> numberOfAlternatingGroups(int[] colors, int[][] queries) {
        int n = colors.length;
        TreeSet<Integer> set = new TreeSet<>();
        BIT bit = new BIT(n);

        for (int i = 0; i < n; i++) {
            if (colors[i] == colors[(i + 1) % n]) {
                add(set, bit, n, i);
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int[] query : queries) {
            if (query[0] == 1) {
                if (set.isEmpty()) {
                    res.add(n);
                } else {
                    int[] ans = bit.query(query[1]);
                    res.add(ans[1] - ans[0] * (query[1] - 1));
                }
            } else {
                int i = query[1];
                if (colors[i] == query[2]) {
                    continue;
                }
                int pre = (i - 1 + n) % n;
                int next = (i + 1) % n;
                if (colors[pre] == colors[i]) {
                    delete(set, bit, n, pre);
                }
                if (colors[i] == colors[next]) {
                    delete(set, bit, n, i);
                }
                colors[i] ^= 1;
                if (colors[pre] == colors[i]) {
                    add(set, bit, n, pre);
                }
                if (colors[i] == colors[next]) {
                    add(set, bit, n, i);
                }
            }
        }
        return res;
    }

    private void add(TreeSet<Integer> set, BIT bit, int n, int i) {
        if (set.isEmpty()) {
            bit.update(n, 1);
        } else {
            update(set, bit, n, i, 1);
        }
        set.add(i);
    }

    private void update(TreeSet<Integer> set, BIT bit, int n, int i, int op) {
        Integer pre = set.floor(i);
        if (pre == null) {
            pre = set.last();
        }
        Integer next = set.ceiling(i);
        if (next == null) {
            next = set.first();
        }
        bit.update((next - pre + n - 1) % n + 1, -op);
        bit.update((i - pre + n) % n, op);
        bit.update((next - i + n) % n, op);
    }

    private void delete(TreeSet<Integer> set, BIT bit, int n, int i) {
        set.remove(i);
        if (set.isEmpty()) {
            bit.update(n, -1);
        } else {
            update(set, bit, n, i, -1);
        }
    }

    private static class BIT {

        private int[][] tree;

        public BIT(int n) {
            tree = new int[n + 1][2];
        }

        public void update(int size, int op) {
            for (int i = tree.length - size; i < tree.length; i += i & -i) {
                tree[i][0] += op;
                tree[i][1] += op * size;
            }
        }

        public int[] query(int size) {
            int count = 0, sum = 0;
            for (int i = tree.length - size; i > 0; i -= i & -i) {
                count += tree[i][0];
                sum += tree[i][1];
            }
            return new int[] {count, sum};
        }
    }
}
