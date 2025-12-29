package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/14/2019.
 * #0765 https://leetcode.com/problems/couples-holding-hands/
 */
public class CouplesHoldingHands {

    // time O(n * α(n)), space O(n)
    public int minSwapsCouples(int[] row) {
        // #3551相似题
        int m = row.length, n = m / 2;
        UnionFind uf = new UnionFind(m);
        // 因为每队情侣需要坐在一起，所以最后他们需要一个坐在偶数位置而另一个
        // 坐在相邻的奇数位置可以用连通集连通每队情侣的id。因为输入数组可能存
        // 在错位，我们还需要连通数组里面相邻的两个位置。这样k对情侣就可以得到
        // k大小的联通分量（可以把一对情侣看成一个数）。根据#3551的分析，这个
        // 连通分量需要k-1次交换来让所有情侣就位。这样最后的答案就是情侣对数
        // - 连通分量的数量。
        // 示例1:
        // [3,2,1,5,4,6,7,0] 含有两个联通分量：[3,2], [1,5,4,6,7,0]，他
        // 们分别需要0和2次交换。
        for (int i = 0; i < m; i += 2) { // O(n)
            int id1 = row[i], id2 = row[i + 1];
            // 连通情侣1
            if (id1 % 2 == 0) {
                uf.union(id1, id1 + 1);
            } else {
                uf.union(id1, id1 - 1);
            }
            // 连通情侣2
            if (id2 % 2 == 0) {
                uf.union(id2, id2 + 1);
            } else {
                uf.union(id2, id2 - 1);
            }
            // 连通相邻的两个错位的人，注意他们也可能是一对情侣。比如上例中的连通
            // 分量[3,2]。
            uf.union(id1, id2);
        }
        return n - uf.countComponents();
    }

    private class UnionFind {

        private int count;
        private int[] root;
        private int[] rank;

        public UnionFind(int n) {
            this.count = n;
            root = new int[n];
            rank = new int[n];
            Arrays.fill(rank, 1);
            Arrays.setAll(root, i -> i);
        }

        public int find(int x) {
            if (x != root[x]) {
                root[x] = find(root[x]);
            }
            return root[x];
        }

        public void union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            if (rank[rootX] > rank[rootY]) {
                rank[rootX] += rank[rootY];
                root[rootY] = rootX;
            } else {
                rank[rootY] += rank[rootX];
                root[rootX] = rootY;
            }
            this.count--;
        }

        public int countComponents() {
            return this.count;
        }
    }
}
