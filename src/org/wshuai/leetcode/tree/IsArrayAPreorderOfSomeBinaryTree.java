package org.wshuai.leetcode.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 01/16/2026.
 * #2764 https://leetcode.com/problems/is-array-a-preorder-of-some-binary-tree/
 */
public class IsArrayAPreorderOfSomeBinaryTree {

    // time O(n), space O(n)
    public boolean isPreorder(List<List<Integer>> nodes) {
        // 根据前序遍历是按照每个子树的根结点，左子树然后右子树的顺序的性质
        if (nodes.get(0).get(1) != -1) {
            return false;
        }
        int n = nodes.size();
        // 维护一个栈存当前从根结点到当前节点路径上经过的点
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(nodes.get(0).get(0));
        for (int i = 1; i < n; i++) {
            int id = nodes.get(i).get(0), pid = nodes.get(i).get(1);
            // 对于每个节点应该都能在栈中找到自己的父结点
            while (!stack.isEmpty() && stack.peek() != pid) {
                stack.pop();
            }
            // 找不到则输入不合法
            if (stack.isEmpty()) {
                return false;
            }
            stack.push(id);
        }
        return true;
    }
}
