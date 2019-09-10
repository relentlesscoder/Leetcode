package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.DesignSearchAutocompleteSystem;

public class DesignSearchAutocompleteSystemTest {
    @Test
    public void testcase(){
        DesignSearchAutocompleteSystem ds = new DesignSearchAutocompleteSystem(new String[]{"i love you","island","iroman","i love leetcode"}, new int[]{5,3,2,2});
        ds.input('i');
        ds.input(' ');
        ds.input('a');
        ds.input('#');
    }

    @Test
    public void testcase1(){
        String curr = "12";
        char c = 'a';
        curr += c;
    }
}
