package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 09/23/2019.
 * #0863 https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 */
public class AllNodesDistanceKInBinaryTree {

    private List<Integer> res;
    private Map<TreeNode, Integer> distance;

    // time O(n), space O(n)
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        res = new ArrayList<>();
        distance = new HashMap<>();
        findTarget(root, target);
        findNodes(root, 0, K);
        return res;
    }

    // find the target node and record the distance from
    // it back to the root
    private int findTarget(TreeNode root, TreeNode target) {
        if (root == null) {
            return -1;
        }
        if (root == target) {
            distance.put(root, 0);
            return 0;
        }
        int left = findTarget(root.left, target);
        if (left >= 0) {
            distance.put(root, left + 1);
            return left + 1;
        }
        int right = findTarget(root.right, target);
        if (right >= 0) {
            distance.put(root, right + 1);
            return right + 1;
        }
        return -1;
    }

    private void findNodes(TreeNode root, int dist, int k) {
        if (root == null) {
            return;
        }
        if (distance.containsKey(root)) {
            dist = distance.get(root);
        }
        if (dist == k) {
            res.add(root.val);
        }
        findNodes(root.left, dist + 1, k);
        findNodes(root.right, dist + 1, k);
    }
}
