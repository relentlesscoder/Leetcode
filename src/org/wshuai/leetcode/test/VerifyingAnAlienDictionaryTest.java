package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.VerifyingAnAlienDictionary;

public class VerifyingAnAlienDictionaryTest {
    @Test
    public void testcase(){
        VerifyingAnAlienDictionary va = new VerifyingAnAlienDictionary();
        boolean valid = va.isAlienSorted(new String[]{"hello","leetcode"}, "hlabcdefgijkmnopqrstuvwxyz");
    }
}
