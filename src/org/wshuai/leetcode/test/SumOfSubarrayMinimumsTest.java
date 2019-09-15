package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.SumOfSubarrayMinimums;

public class SumOfSubarrayMinimumsTest {
    @Test
    public void testcase(){
        SumOfSubarrayMinimums ss = new SumOfSubarrayMinimums();
        int sum = ss.sumSubarrayMins(new int[]{3,1,2,4});
    }
}
