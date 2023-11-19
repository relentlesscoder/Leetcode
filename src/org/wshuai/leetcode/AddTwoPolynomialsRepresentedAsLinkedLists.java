package org.wshuai.leetcode;

/**
 * Created by Wei on 11/10/2020.
 * #1634 https://leetcode.com/problems/add-two-polynomials-represented-as-linked-lists/
 */
public class AddTwoPolynomialsRepresentedAsLinkedLists {

    // time O(m + n)
    public PolyNode addPoly(PolyNode poly1, PolyNode poly2) {
        PolyNode root = new PolyNode(), cur = root;
        while (poly1 != null || poly2 != null) {
            if (poly1 == null) {
                cur.next = new PolyNode(poly2.coefficient, poly2.power);
                poly2 = poly2.next;
            } else if (poly2 == null) {
                cur.next = new PolyNode(poly1.coefficient, poly1.power);
                poly1 = poly1.next;
            } else if (poly2.power > poly1.power) {
                cur.next = new PolyNode(poly2.coefficient, poly2.power);
                poly2 = poly2.next;
            } else if (poly1.power > poly2.power) {
                cur.next = new PolyNode(poly1.coefficient, poly1.power);
                poly1 = poly1.next;
            } else if (poly1.coefficient + poly2.coefficient == 0) {
                poly1 = poly1.next;
                poly2 = poly2.next;
                continue;
            } else {
                cur.next = new PolyNode(poly1.coefficient + poly2.coefficient, poly1.power);
                poly1 = poly1.next;
                poly2 = poly2.next;
            }
            cur = cur.next;
        }
        return root.next;
    }

    //Definition for polynomial singly-linked list.
    private class PolyNode {

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
