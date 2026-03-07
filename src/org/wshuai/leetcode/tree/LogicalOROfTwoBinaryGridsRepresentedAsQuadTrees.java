package org.wshuai.leetcode.tree;

/**
 * Created by Wei on 03/11/2020.
 * #0558 https://leetcode.com/problems/logical-or-of-two-binary-grids-represented-as-quad-trees/
 */
public class LogicalOROfTwoBinaryGridsRepresentedAsQuadTrees {

    // time O(n^2), space O(log(n))
    public Node intersect(Node quadTree1, Node quadTree2) {
        // 都是叶子节点则交集还是叶子节点
        if (quadTree1.isLeaf && quadTree2.isLeaf) {
            return new Node(quadTree1.val || quadTree2.val, true);
        }
        // 其中一个为叶子节点则取决于该叶子节点的值:
        //   1. 叶子节点的值为 true 则交集还是值为 true 的叶子节点
        //   2. 叶子节点的值为 false 则交集为值等于另外一个节点
        if (quadTree1.isLeaf) {
            return quadTree1.val ? quadTree1 : quadTree2;
        }
        if (quadTree2.isLeaf) {
            return quadTree2.val ? quadTree2 : quadTree1;
        }
        // 都不是则取四个子节点的交集
        Node topLeft = intersect(quadTree1.topLeft, quadTree2.topLeft);
        Node topRight = intersect(quadTree1.topRight, quadTree2.topRight);
        Node bottomLeft = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
        Node bottomRight = intersect(quadTree1.bottomRight, quadTree2.bottomRight);
        // 判断交集是否为叶子节点
        boolean isLeaf = topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf
                && topLeft.val == topRight.val && topLeft.val == bottomLeft.val && topLeft.val == bottomRight.val;
        return isLeaf ? new Node(topLeft.val, true) :
                new Node(topLeft.val, false, topLeft, topRight, bottomLeft, bottomRight);
    }

    /**
     * Definition for a QuadTree node.
     */
    private static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf,
                    Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }
}
