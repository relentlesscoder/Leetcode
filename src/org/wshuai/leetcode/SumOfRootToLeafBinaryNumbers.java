package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 8/9/19.
 * #1022 https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/
 */
public class SumOfRootToLeafBinaryNumbers {
    public int sumRootToLeaf(TreeNode root) {
        List<List<Integer>> lst = new ArrayList<List<Integer>>();
        List<Integer> curr = new ArrayList<Integer>();
        int[] max = new int[1];
        max[0] = Integer.MIN_VALUE;
        DFS(lst, root, curr, max);
        int[] aux = new int[max[0]];
        for(int i = 0; i < max[0]; i++){
            if(i == 0){
                aux[i] = 1;
            }else if(i == 1){
                aux[i] = 2;
            }else{
                aux[i] = aux[i-1]*2;
            }
        }
        int sum = 0;
        for(List<Integer> num: lst){
            int idx = num.size() - 1;
            int csum = 0;
            int j = 0;
            while(idx >= 0){
                if(num.get(idx) == 1){
                    csum += aux[j];
                }
                idx--;
                j++;
            }
            sum += csum;
        }
        return sum;
    }

    private void DFS(List<List<Integer>> lst, TreeNode node, List<Integer> curr, int[] max){
        curr.add(node.val);
        if(node.left != null){
            List<Integer> left = new ArrayList<Integer>(curr);
            DFS(lst, node.left, left, max);
        }
        if(node.right != null){
            List<Integer> right = new ArrayList<Integer>(curr);
            DFS(lst, node.right, right, max);
        }
        if(node.left == null && node.right == null){
            max[0] = max[0] < curr.size() ? curr.size() : max[0];
            lst.add(curr);
            return;
        }
    }
}
