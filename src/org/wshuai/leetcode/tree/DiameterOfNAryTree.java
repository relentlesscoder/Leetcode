package org.wshuai.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 07/23/2020.
 * #1522 https://leetcode.com/problems/diameter-of-n-ary-tree/
 */
public class DiameterOfNAryTree {

    private int res = 0;

    // time O(n), space O(n)
    public int diameter(Node root) {
        res = 0;
        dfs(root);
        return res;
    }

    public int dfs(Node root) {
        if (root == null || root.children.isEmpty()) {
            return 0;
        }
        int max = 0, second = 0;
        for (Node child : root.children) {
            int len = 1 + dfs(child);
            if (len > max) {
                second = max;
                max = len;
            } else if (len > second) {
                second = len;
            }
        }
        res = Math.max(res, max + second);
        return max;
    }

    private static class Node {
        public int val;
        public List<Node> children;


        public Node() {
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;
}
