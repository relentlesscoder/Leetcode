package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 12/07/2020.
 * #1650 https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/
 */
public class LowestCommonAncestorOfABinaryTreeIII {

    // time O(h)
    // same as #160
    public Node lowestCommonAncestor(Node p, Node q) {
        Node a = p, b = q;
        while(a != b){
            a = a == null ? q : a.parent;
            b = b == null ? p : b.parent;
        }
        return a;
    }

    // time O(h), space O(h)
    public Node lowestCommonAncestorHashSet(Node p, Node q) {
        Set<Node> visited = new HashSet<>();
        while (true) {
            if (p != null && !visited.add(p)) {
                return p;
            }
            if (q != null && !visited.add(q)) {
                return q;
            }
            p = p == null ? null : p.parent;
            q = q == null ? null : q.parent;
        }
    }

    // Definition for a Node.
    private class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }
}
