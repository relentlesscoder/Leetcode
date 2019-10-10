package org.wshuai.algorithm.test;

import org.junit.Test;
import org.wshuai.algorithm.dynamicProgramming.LevenshteinDistance;

public class LevenshteinDistanceTest {
	@Test
	public void testcase(){
		LevenshteinDistance tld = new LevenshteinDistance();
		int wd = tld.wordDistance("sitting", "kitten");
	}
}
