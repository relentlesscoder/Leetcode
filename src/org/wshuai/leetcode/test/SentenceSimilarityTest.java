package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.SentenceSimilarity;

import java.util.ArrayList;
import java.util.List;

public class SentenceSimilarityTest {
    @Test
    public void testcase(){
        SentenceSimilarity ss = new SentenceSimilarity();
        boolean s = ss.areSentencesSimilar(new String[]{"great"}, new String[]{"great"}, new ArrayList<List<String>>());
    }
}
