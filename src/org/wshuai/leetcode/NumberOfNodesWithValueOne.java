package org.wshuai.leetcode;

/**
 * Created by Wei on 02/01/2026.
 * #2445 https://leetcode.com/problems/number-of-nodes-with-value-one/
 */
public class NumberOfNodesWithValueOne {

    // time O(n), space O(n)
    public int numberOfNodes(int n, int[] queries) {
        // 更简洁的写法，用异或来计算节点的值
        int res = 0;
        // 现在数组记录每个节点在所有操作后的值
        int[] nodes = new int[n + 1];
        // 对节点进行操作
        for (int x : queries) {
            nodes[x] ^= 1;
        }
        // 计算所有操作之后节点的最终值
        for (int i = 1; i <= n; i++) {
            nodes[i] ^= nodes[i >> 1];
            res += nodes[i];
        }
        return res;
    }

    // time O(n), space O(n)
    public int numberOfNodesCountOperations(int n, int[] queries) {
        // 对于每一个操作 q 来说，它会将自己和子节点 q * 2 和 q * 2 + 1 以及子节点的子节点
        // 都翻转。这样对每一节点来说，它翻转的次数就等于它父节点翻转的次数加上它自己翻转的次数。
        int res = 0;
        // 用数组计算每个节点的翻转次数
        int[] nodes = new int[n + 1];
        // 记录每个节点的操作数
        for (int x : queries) {
            nodes[x]++;
        }
        // 从 1 开始计算每个节点的总翻转次数
        for (int i = 1; i <= n; i++) {
            nodes[i] += nodes[i >> 1];
            res += (nodes[i] % 2);
        }
        return res;
    }
}
