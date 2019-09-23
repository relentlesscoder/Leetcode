package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.WordLadderII;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Wei on 12/4/16.
 */
public class WordLadderIITest {
	@Test
	public void testcase() {
		WordLadderII wl = new WordLadderII();
		Set<String> set = new HashSet<String>();
		set.add("hot");
		set.add("dot");
		set.add("dog");
		set.add("lot");
		set.add("log");
		List<List<String>> res = wl.findLadders("hit", "cog", set);
	}
}
