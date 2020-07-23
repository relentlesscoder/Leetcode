package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 07/23/2020.
 * #1522 https://leetcode.com/problems/diameter-of-n-ary-tree/
 */
public class DiameterOfNAryTree {

    private int d;

    // time O(n)
    public int diameter(Node root) {
        d = 0;
        if(root == null){
            return 0;
        }
        dfs(root);
        return d;
    }

    private int dfs(Node root){
        if(root == null || root.children.size() == 0){
            return 0;
        }
        int max = 0, secondMax = 0;
        for(Node child : root.children){
            int cur = 1 + dfs(child);
            if(cur > max){
                secondMax = max;
                max = cur;
            }else if(cur > secondMax){
                secondMax = cur;
            }
        }
        d = Math.max(max + secondMax, d);
        return max;
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
    };
}
