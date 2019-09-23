package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.AlienDictionary;

/**
 * Created by Wei on 7/4/17.
 */
public class AlienDictionaryTest {
	@Test
	public void testcase() {
		AlienDictionary ad = new AlienDictionary();
		String x = ad.alienOrder(new String[]{
				"wrt", "wrf", "er", "ett", "rftt"});
	}
}
