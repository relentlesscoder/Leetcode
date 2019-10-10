package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.EvaluateDivision;

/**
 * Created by Wei on 11/21/2016.
 */
public class EvaluateDivisionTest {
	@Test
	public void testcase() {
		EvaluateDivision ed = new EvaluateDivision();
		String[][] equs = new String[][]{{"a", "b"}, {"b", "c"}};
		double[] vals = new double[]{2.0, 3.0};
		String[][] qus = new String[][]{{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};
		double[] res = ed.calcEquation(equs, vals, qus);
	}
}
