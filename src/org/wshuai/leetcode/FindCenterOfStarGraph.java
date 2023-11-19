package org.wshuai.leetcode;

/**
 * Created by Wei on 05/20/2021.
 * #1791 https://leetcode.com/problems/find-center-of-star-graph/
 */
public class FindCenterOfStarGraph {

    public int findCenter(int[][] edges) {
        return edges[0][0] == edges[1][0] || edges[0][0] == edges[1][1] ? edges[0][0] : edges[0][1];
    }
}
