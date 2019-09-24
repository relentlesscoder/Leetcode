package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 9/23/2019.
 * #863 https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 */
public class AllNodesDistanceKInBinaryTree {
  private Map<TreeNode, Integer> map;
  private List<Integer> res;

  public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
    res = new ArrayList<>();
    map = new HashMap<>();
    find(root, target);
    dfs(root, 0, K);
    return res;
  }

  private int find(TreeNode node, TreeNode target){
    if(node == null){
      return -1;
    }
    if(node == target){
      map.put(node, 0);
      return 0;
    }
    int left = find(node.left, target);
    if(left >= 0){
      map.put(node, left + 1);
      return left + 1;
    }
    int right = find(node.right, target);
    if(right >= 0){
      map.put(node, right + 1);
      return right + 1;
    }
    return -1;
  }

  private void dfs(TreeNode node, int len, int k){
    if(node == null){
      return;
    }
    if(map.containsKey(node)){
      len = map.get(node);
    }
    if(len == k){
      res.add(node.val);
    }
    dfs(node.left, len + 1, k);
    dfs(node.right, len + 1, k);
  }
}
