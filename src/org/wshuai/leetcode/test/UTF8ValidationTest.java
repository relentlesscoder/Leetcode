package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.UTF8Validation;

public class UTF8ValidationTest {
    @Test
    public void testcase(){
        UTF8Validation utf = new UTF8Validation();
        utf.validUtf8(new int[]{197,130,1});
    }
}
