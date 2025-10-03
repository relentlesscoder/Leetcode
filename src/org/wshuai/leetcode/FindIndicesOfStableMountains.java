package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/03/2025.
 * #3285 https://leetcode.com/problems/find-indices-of-stable-mountains/
 */
public class FindIndicesOfStableMountains {

    // time O(n), space O(n)
    public List<Integer> stableMountains(int[] height, int threshold) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < height.length; i++) {
            if (height[i - 1] > threshold) {
                res.add(i);
            }
        }
        return res;
    }
}
