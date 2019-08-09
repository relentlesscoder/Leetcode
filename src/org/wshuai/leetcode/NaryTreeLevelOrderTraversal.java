package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 8/9/19.
 * #429 https://leetcode.com/problems/n-ary-tree-level-order-traversal/
 */
public class NaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(NaryTreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null){
            return res;
        }
        List<NaryTreeNode> curr = new ArrayList<NaryTreeNode>();
        List<NaryTreeNode> next = new ArrayList<NaryTreeNode>();
        curr.add(root);
        while(!curr.isEmpty()){
            List<Integer> lst = new ArrayList<Integer>();
            for(NaryTreeNode node: curr){
                lst.add(node.val);
                next.addAll(node.children);
            }
            res.add(lst);
            curr = next;
            next = new ArrayList<NaryTreeNode>();
        }
        return res;
    }
}
