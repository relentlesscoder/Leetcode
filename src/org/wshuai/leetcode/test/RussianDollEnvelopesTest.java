package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.RussianDollEnvelopes;

/**
 * Created by Wei on 2/20/17.
 */
public class RussianDollEnvelopesTest {
	@Test
	public void test() {
		RussianDollEnvelopes rd = new RussianDollEnvelopes();
		int d = rd.maxEnvelopes(new int[][]{
				{4, 5}, {4, 6}, {6, 7}, {2, 3}, {1, 1}
		});
	}
}
