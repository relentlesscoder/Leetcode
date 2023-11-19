package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 09/11/2023.
 * #2610 https://leetcode.com/problems/convert-an-array-into-a-2d-array-with-conditions/
 */
public class ConvertAnArrayIntoA2DArrayWithConditions {

    // time O(n), space O(1)
    public List<List<Integer>> findMatrix(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int maxFreq = 0;
        int[] freq = new int[201];
        for (int num : nums) {
            freq[num]++;
            maxFreq = Math.max(maxFreq, freq[num]);
        }
        while (maxFreq-- > 0) {
            res.add(new ArrayList<>());
        }
        for (int i = 1; i <= 200; i++) {
            int curr = freq[i];
            for (int j = 0; j < curr; j++) {
                res.get(j).add(i);
            }
        }
        return res;
    }
}
