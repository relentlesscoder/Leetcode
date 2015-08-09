package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.TwoSum;

import static org.junit.Assert.assertEquals;

/**
 * Created by Wei on 8/9/15.
 */
public class TwoSumTest {
    @Test
    public void twoSumShouldReturnCorrectIndexes(){
        int[] array1 = {3, 2, 4};
        int target1 = 6;

        int[] result1 = TwoSum.twoSum(array1, target1);
        assertEquals(result1[0], 2);
        assertEquals(result1[1], 3);
    }
}
