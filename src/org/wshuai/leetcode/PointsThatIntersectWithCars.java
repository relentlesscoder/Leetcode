package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 11/22/2023.
 * #2848 https://leetcode.com/problems/points-that-intersect-with-cars/
 */
public class PointsThatIntersectWithCars {

    // time O(n + MAX), space O(MAX)
    public int numberOfPoints(List<List<Integer>> nums) {
        // 利用差分数组计算有多少个点被至少一辆车覆盖
        int res = 0, max = 0;
        for (List<Integer> list : nums) { // O(n)
            max = Math.max(max, list.get(1));
        }
        int[] diff = new int[max + 2];
        for (List<Integer> list : nums) { // O(n)
            int s = list.get(0), e = list.get(1);
            diff[s]++;
            diff[e + 1]--;
        }
        for (int i = 1, cnt = 0; i <= max; i++) { // O(MAX)
            cnt += diff[i];
            if (cnt > 0) {
                res++;
            }
        }
        return res;
    }
}
