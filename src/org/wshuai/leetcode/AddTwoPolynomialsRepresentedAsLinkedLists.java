package org.wshuai.leetcode;

/**
 * Created by Wei on 11/10/2020.
 * #1634 https://leetcode.com/problems/add-two-polynomials-represented-as-linked-lists/
 */
public class AddTwoPolynomialsRepresentedAsLinkedLists {

    // time O(m + n), space O(1)
    public PolyNode addPoly(PolyNode poly1, PolyNode poly2) {
        PolyNode root = new PolyNode(-1, -1), curr = root;
        while (poly1 != null || poly2 != null) {
            if (poly1 == null) {
                curr.next = poly2;
                poly2 = poly2.next;
            } else if (poly2 == null) {
                curr.next = poly1;
                poly1 = poly1.next;
            } else if (poly1.power < poly2.power) {
                curr.next = poly2;
                poly2 = poly2.next;
            } else if (poly1.power > poly2.power) {
                curr.next = poly1;
                poly1 = poly1.next;
            } else if (poly1.coefficient + poly2.coefficient == 0) { // 两个节点抵消了
                poly2 = poly2.next;
                poly1 = poly1.next;
                continue;
            } else {
                curr.next = new PolyNode(poly1.coefficient + poly2.coefficient, poly1.power);
                poly2 = poly2.next;
                poly1 = poly1.next;
            }
            curr = curr.next;
            // 为了节省空间使用原链表元素，所以一旦节点被加入要断开与原链表节点的连接
            curr.next = null;
        }
        return root.next;
    }

    /**
     * Definition for polynomial singly-linked list.
     **/
    private static class PolyNode {

        int coefficient, power;
        PolyNode next = null;

        PolyNode() {
        }

        PolyNode(int x, int y) {
            this.coefficient = x;
            this.power = y;
        }

        PolyNode(int x, int y, PolyNode next) {
            this.coefficient = x;
            this.power = y;
            this.next = next;
        }
    }
}
