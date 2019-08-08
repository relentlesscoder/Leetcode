package org.wshuai.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wei on 8/8/19.
 * #590 https://leetcode.com/problems/n-ary-tree-postorder-traversal/
 */
public class NaryTreePostorderTraversal {
    public List<Integer> postorder(NaryTreeNode root) {
        LinkedList<Integer> lst = new LinkedList<Integer>();
        if(root == null){
            return lst;
        }
        LinkedList<NaryTreeNode> stack = new LinkedList<NaryTreeNode>();
        stack.add(root);
        while(!stack.isEmpty()){
            NaryTreeNode node = stack.pollLast();
            lst.addFirst(node.val);
            for(NaryTreeNode child: node.children){
                if(child != null){
                    stack.add(child);
                }
            }
        }
        return lst;
    }
}
