package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.HighFive;

public class HighFiveTest {
    @Test
    public void testcase(){
        HighFive hf = new HighFive();
        int[][] items = new int[11][2];
        items[0] = new int[]{1,91};
        items[1] = new int[]{1,92};
        items[2] = new int[]{2,93};
        items[3] = new int[]{2,97};
        items[4] = new int[]{1,60};
        items[5] = new int[]{2,77};
        items[6] = new int[]{1,65};
        items[7] = new int[]{1,87};
        items[8] = new int[]{1,100};
        items[9] = new int[]{2,100};
        items[10] = new int[]{2,76};
        int[][] res = hf.highFive(items);
    }
}
