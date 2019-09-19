package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.ValidateStackSequences;

public class ValidateStackSequencesTest {
    @Test
    public void testcase(){
        ValidateStackSequences vs = new ValidateStackSequences();
        boolean b = vs.validateStackSequences(new int[]{1,2,3,4,5}, new int[]{4,5,3,2,1});
    }
}
