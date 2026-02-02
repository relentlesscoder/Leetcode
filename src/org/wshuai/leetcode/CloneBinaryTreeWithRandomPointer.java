package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 06/17/2020.
 * #1485 https://leetcode.com/problems/clone-binary-tree-with-random-pointer/
 */
public class CloneBinaryTreeWithRandomPointer {

    // time O(n), space O(n)
    public NodeCopy copyRandomBinaryTree(Node root) {
        // 注意本题如果是同一个类的话可以考虑用新节点的 random 代替哈希表存映射关系 (#0138)。
        // 用哈希表存原节点到复制节点的映射
        Map<Node, NodeCopy> map = new HashMap<>();
        // DFS 复制构造新的二叉树并将所有映射存入哈希表
        NodeCopy rootCopy = copyTree(root, map);
        // 根据哈希表中的映射设置每个节点的随机指针
        setRandom(rootCopy, root, map);
        return rootCopy;
    }

    private void setRandom(NodeCopy rootCopy, Node root, Map<Node, NodeCopy> map) {
        if (rootCopy == null) {
            return;
        }
        rootCopy.random = map.get(root.random);
        setRandom(rootCopy.left, root.left, map);
        setRandom(rootCopy.right, root.right, map);
    }

    private NodeCopy copyTree(Node root, Map<Node, NodeCopy> map) {
        if (root == null) {
            return null;
        }
        NodeCopy rootCopy = new NodeCopy(root.val);
        map.put(root, rootCopy);
        rootCopy.left = copyTree(root.left, map);
        rootCopy.right = copyTree(root.right, map);
        return rootCopy;
    }

    /**
     * Definition for Node.
     */
    private static class Node {
        int val;
        Node left;
        Node right;
        Node random;

        Node() {
        }

        Node(int val) {
            this.val = val;
        }

        Node(int val, Node left, Node right, Node random) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.random = random;
        }
    }

    /**
     * Definition for NodeCopy.
     */
    private static class NodeCopy {
        int val;
        NodeCopy left;
        NodeCopy right;
        NodeCopy random;

        NodeCopy() {
        }

        NodeCopy(int val) {
            this.val = val;
        }

        NodeCopy(int val, NodeCopy left, NodeCopy right, NodeCopy random) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.random = random;
        }
    }
}
