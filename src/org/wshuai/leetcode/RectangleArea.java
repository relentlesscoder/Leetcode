package org.wshuai.leetcode;

/**
 * Created by Wei on 01/24/2020.
 * #0223 https://leetcode.com/problems/rectangle-area/
 */
public class RectangleArea {

    // time O(1), space O(1)
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int areaA = (ax2 - ax1) * (ay2 - ay1);
        int areaB = (bx2 - bx1) * (by2 - by1);
        int xOverlap = Math.min(ax2, bx2) - Math.max(ax1, bx1);
        int yOverlap = Math.min(ay2, by2) - Math.max(ay1, by1);
        return areaA + areaB - (xOverlap > 0 && yOverlap > 0 ? xOverlap * yOverlap : 0);
    }
}
