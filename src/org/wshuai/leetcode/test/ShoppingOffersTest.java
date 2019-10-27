package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.ShoppingOffers;

import java.util.Arrays;

public class ShoppingOffersTest {
	@Test
	public void testcase(){
		ShoppingOffers so = new ShoppingOffers();
		int res = so.shoppingOffers(Arrays.asList(2, 5),
			Arrays.asList(Arrays.asList(3, 0, 5), Arrays.asList(1, 2, 10)), Arrays.asList(3, 2));
	}
}
