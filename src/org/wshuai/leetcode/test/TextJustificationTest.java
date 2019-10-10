package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.TextJustification;

import java.util.List;

/**
 * Created by Wei on 10/11/2016.
 */
public class TextJustificationTest {
	@Test
	public void testcase1() {
		TextJustification tj = new TextJustification();
		List<String> lst = tj.fullJustify(new String[]{"a"}, 1);
	}

	@Test
	public void testcase2() {
		TextJustification tj = new TextJustification();
		List<String> lst = tj.fullJustify(new String[]{""}, 0);
	}

	@Test
	public void testcase3() {
		TextJustification tj = new TextJustification();
		List<String> lst = tj.fullJustify(new String[]{"What", "must", "be", "shall", "be."}, 12);
	}
}
