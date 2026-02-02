package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 09/24/2016.
 * #0331 https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/
 */
public class VerifyPreorderSerializationOfABinaryTree {

    // time O(n), space O(1)
    public boolean isValidSerialization(String preorder) {
        // 性质: 树(图)所有节点的入度之和等于出度之和。为了方便计算将空节点
        // 是为入度为 1 出度为 0 。
        String[] nodes = preorder.split(",");
        int degree = 0, n = nodes.length;
        for (int i = 0; i < n; i++) {
            // 注意根结点没有入度
            if (i != 0 && --degree < 0) {
                return false;
            }
            // 如果节点不为空则出度为 2
            if (!nodes[i].equals("#")) {
                degree += 2;
            }
        }
        return degree == 0;
    }

    // time O(n), space O(n)
    public boolean isValidSerializationXXL(String preorder) {
        // 从叶子节点开始玩消消乐，如果两个子节点都是 # 则将父节点变为 # (消掉)。
        // 如果最后只剩一个节点且为 # 则输入合法。
        LinkedList<String> stack = new LinkedList<>();
        String[] nodes = preorder.split(",");
        int n = nodes.length;
        for (int i = 0; i < n; i++) {
            stack.push(nodes[i]);
            while (stack.size() >= 3
                    && stack.get(0).equals("#")
                    && stack.get(1).equals("#")
                    && !stack.get(2).equals("#")) {
                stack.pop();
                stack.pop();
                stack.pop();
                stack.push("#");
            }
        }
        return stack.size() == 1 && stack.peek().equals("#");
    }
}
