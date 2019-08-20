package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.LongestContinuousIncreasingSubsequence;

public class LongestContinuousIncreasingSubsequenceTest {
    @Test
    public void testcase(){
        LongestContinuousIncreasingSubsequence lc = new LongestContinuousIncreasingSubsequence();
        int res = lc.findLengthOfLCIS(new int[]{1,2,3,7,8,9});
    }
}
