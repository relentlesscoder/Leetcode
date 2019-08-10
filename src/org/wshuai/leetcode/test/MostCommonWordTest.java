package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.MostCommonWord;

public class MostCommonWordTest {
    @Test
    public void testcase(){
        MostCommonWord mc = new MostCommonWord();
        String res = mc.mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", new String[]{"hit"});
    }
}
