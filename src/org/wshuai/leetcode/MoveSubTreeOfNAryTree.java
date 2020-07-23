package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 07/23/2020.
 * #1516 https://leetcode.com/problems/move-sub-tree-of-n-ary-tree/
 */
public class MoveSubTreeOfNAryTree {

    // time O(n)
    public Node moveSubTree(Node root, Node p, Node q) {
        // p is already a direct child of q
        if(q.children.contains(p)){
            return root;
        }
        // find parents of p and q
        Node[] parents = new Node[2];
        findParents(root, p, q, parents);
        // Node p is in the sub-tree of node q
        if(inSubtree(q, p)){
            parents[1].children.remove(q);
            q.children.add(p);
            // p is the root node
            if(parents[0] == null){
                return q;
            }
            int index = parents[0].children.indexOf(p);
            parents[0].children.set(index, q);
            return root;
        }
        q.children.add(p);
        parents[0].children.remove(p);
        return root;
    }

    private void findParents(Node root, Node p, Node q, Node[] parents){
        if(root == null){
            return;
        }
        for(Node next : root.children){
            if(next.val == p.val){
                parents[0] = root;
            }
            if(next.val == q.val){
                parents[1] = root;
            }
            findParents(next, p, q, parents);
        }
    }

    private boolean inSubtree(Node node, Node root){
        if(root == null){
            return false;
        }
        if(root.val == node.val){
            return true;
        }
        for(Node next : root.children){
            if(inSubtree(node, next)){
                return true;
            }
        }
        return false;
    }

    private class Node {
        public int val;
        public List<Node> children;


        public Node() {
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val,ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
