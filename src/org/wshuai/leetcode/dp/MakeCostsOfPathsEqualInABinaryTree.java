package org.wshuai.leetcode.dp;

/**
 * Created by Wei on 01/31/2026.
 * #2673 https://leetcode.com/problems/make-costs-of-paths-equal-in-a-binary-tree/
 */
public class MakeCostsOfPathsEqualInABinaryTree {

    // time O(n), space O(1)
    public int minIncrements(int n, int[] cost) {
        // 从两个叶子节点的父节点开始考虑，从根节点到该节点的路径完全一样所以必须让两个
        // 叶子节点的值相同。最优方法是将较小的节点设为与较大节点一样。用同样的方法继续
        // 往上去计算上面节点的代价，需要累计从叶子到该节点的总代价。
        int res = 0;
        // 从最后一个非叶节点开始到根结点
        for (int i = n / 2 - 1; i >= 0; i--) {
            // 将较小的那边的代价增加到与较大待将相等
            res += Math.abs(cost[i * 2 + 1] - cost[i * 2 + 2]);
            // 累计代价值
            cost[i] += Math.max(cost[i * 2 + 1], cost[i * 2 + 2]);
        }
        return res;
    }
}
