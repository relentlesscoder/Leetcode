package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Wei on 10/29/16.
 * #437 https://leetcode.com/problems/path-sum-iii/
 */
public class PathSumIII {
  public int pathSum(TreeNode root, int sum) {
    if(root == null){
      return 0;
    }
    List<TreeNode> lst = new ArrayList<TreeNode>();
    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    queue.offer(root);
    while(!queue.isEmpty()){
      TreeNode node = queue.poll();
      lst.add(node);
      if(node.left != null){
        queue.offer(node.left);
      }
      if(node.right != null){
        queue.offer(node.right);
      }
    }
    CountObj cnt = new CountObj();
    for(TreeNode node: lst){
      pathSumUtil(node, sum, 0, cnt);
    }
    return cnt.count;
  }

  public void pathSumUtil(TreeNode root, int sum, int csum, CountObj cnt){
    if(root == null){
      return;
    }
    int nsum = csum + root.val;
    if(nsum == sum){
      cnt.count++;
    }

    pathSumUtil(root.left, sum, nsum, cnt);
    pathSumUtil(root.right, sum, nsum, cnt);
  }
}

class CountObj{
  int count = 0;
}