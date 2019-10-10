package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.ShortestDistanceFromAllBuildings;

/**
 * Created by Wei on 7/19/17.
 */
public class ShortestDistanceFromAllBuildingsTest {
	@Test
	public void testcase() {
		ShortestDistanceFromAllBuildings sd = new ShortestDistanceFromAllBuildings();
		int min = sd.shortestDistance(new int[][]{
				{1, 0, 2, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}
		});
	}
}
