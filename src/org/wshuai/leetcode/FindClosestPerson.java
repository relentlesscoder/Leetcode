package org.wshuai.leetcode;

/**
 * Created by Wei on 09/20/2025.
 * #3516 https://leetcode.com/problems/find-closest-person/
 */
public class FindClosestPerson {

    // time O(1), space O(1)
    public int findClosest(int x, int y, int z) {
        int t1 = Math.abs(x - z);
        int t2 = Math.abs(y - z);
        if (t1 == t2) {
            return 0;
        }
        return t1 < t2 ? 1 : 2;
    }
}
