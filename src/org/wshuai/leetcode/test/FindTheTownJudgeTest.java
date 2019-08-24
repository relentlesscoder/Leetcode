package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.FindTheTownJudge;

public class FindTheTownJudgeTest {
    @Test
    public void testcase(){
        FindTheTownJudge tj = new FindTheTownJudge();
        int[][] trust = new int[2][2];
        trust[0] = new int[]{1,3};
        trust[1] = new int[]{2,3};
        int index = tj.findJudge(3, trust);
    }
}
