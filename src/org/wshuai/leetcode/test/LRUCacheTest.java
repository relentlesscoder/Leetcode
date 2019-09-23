package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.LRUCache;

import static org.junit.Assert.assertEquals;

/**
 * Created by Wei on 8/18/2016.
 */
public class LRUCacheTest {
	@Test
	public void testcase1() {
		LRUCache cache = new LRUCache(1);
		cache.set(2, 1);
		int v1 = cache.get(2);
		cache.set(3, 2);
		int v2 = cache.get(2);
		int v3 = cache.get(3);
	}

	@Test
	public void testcase2() {
		LRUCache cache = new LRUCache(1);
		cache.set(2, 1);
		int v1 = cache.get(2);
	}

	@Test
	public void testcase3() {
		LRUCache cache = new LRUCache(2);
		int v1 = cache.get(2);
		cache.set(2, 6);
		int v2 = cache.get(1);
		cache.set(1, 5);
		cache.set(1, 2);
		int v3 = cache.get(1);
		int v4 = cache.get(2);
	}

	@Test
	public void testcase4() {
		LRUCache cache = new LRUCache(2);
		cache.set(2, 1);
		cache.set(1, 1);
		cache.set(2, 3);
		cache.set(4, 1);
		int v1 = cache.get(1);
		int v2 = cache.get(2);
	}
}
