package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 12/29/2023.
 * #2951 https://leetcode.com/problems/find-the-peaks/
 */
public class FindThePeaks {

    // time O(n), space O(n)
    public List<Integer> findPeaks(int[] mountain) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < mountain.length - 1; i++) {
            if (mountain[i] > mountain[i - 1] && mountain[i] > mountain[i + 1]) {
                res.add(i);
            }
        }
        return res;
    }
}
