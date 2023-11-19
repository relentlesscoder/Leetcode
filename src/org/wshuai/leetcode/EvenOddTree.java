package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 10/04/2020.
 * #1609 https://leetcode.com/problems/even-odd-tree/
 */
public class EvenOddTree {

    // time O(n), space O(n)
    public boolean isEvenOddTree(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        TreeNode prev = null;
        boolean odd = true;
        queue.offerLast(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size > 0){
                TreeNode cur = queue.pollFirst();
                if((cur.val % 2 == 1) != odd){
                    return false;
                }
                if(prev != null && (cur.val == prev.val || (cur.val > prev.val) != odd)){
                    return false;
                }
                prev = cur;
                size--;
                if(cur.left != null){
                    queue.offerLast(cur.left);
                }
                if(cur.right != null){
                    queue.offerLast(cur.right);
                }
            }
            odd = !odd;
            prev = null;
        }
        return true;
    }
}
