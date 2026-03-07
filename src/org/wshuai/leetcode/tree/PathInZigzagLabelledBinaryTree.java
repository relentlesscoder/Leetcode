package org.wshuai.leetcode.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wei on 09/11/2019.
 * #1104 https://leetcode.com/problems/path-in-zigzag-labelled-binary-tree/
 */
public class PathInZigzagLabelledBinaryTree {

    // time O(log(n)), space O(log(n))
    public List<Integer> pathInZigZagTree(int label) {
        LinkedList<Integer> res = new LinkedList<>();
        // 在一个从 1 开始的满二叉树中，节点 x 所在的层数就是 x 的二进制位数。
        // 层    二进制
        //  1    1
        //  2    10, 11
        //  3    100, 101, 110, 111
        //  4    1000, 1001, 1010, 1011, 1100, 1101, 1110, 1111
        //    ...
        int level = 32 - Integer.numberOfLeadingZeros(label);
        // 从下往上遍历每一层
        while (level > 0) {
            res.offerFirst(label);
            int actual = label;
            if (level % 2 == 0) { // 如果是偶数层，计算标签值在树中的实际位置
                actual = (1 << level) - 1 - label + (1 << (level - 1));
            }
            int next = actual / 2; // 用实际位置计算实际的父节点的位置
            if (level % 2 == 1) { // 如果下一层是偶数层
                int upper = level - 1;
                // 将实际位置再转换成标签值
                next = (1 << upper) - 1 - next + (1 << (upper - 1));
            }
            level--;
            label = next; // 继续处理父节点
        }
        return res;
    }
}
