package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/20/2025.
 * #3697 https://leetcode.com/problems/compute-decimal-representation/
 */
public class ComputeDecimalRepresentation {

    // time O(log(n)), space O(log(n))
    public int[] decimalRepresentation(int n) {
        List<Integer> nums = new ArrayList<>();
        int pow = 1;
        while (n > 0) {
            if (n % 10 > 0) {
                nums.add((n % 10) * pow);
            }
            n /= 10;
            pow *= 10;
        }
        int[] res = new int[nums.size()];
        for (int i = res.length - 1; i >= 0; i--) {
            res[res.length - i - 1] = nums.get(i);
        }
        return res;
    }
}
