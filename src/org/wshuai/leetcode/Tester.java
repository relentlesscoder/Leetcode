package org.wshuai.leetcode;

import org.junit.Test;

public class Tester {
	@Test
	public void testcase(){
		OnlineMajorityElementInSubarray ome = new OnlineMajorityElementInSubarray(new int[]{1,1,2,2,1,1});
		int res = ome.query(2, 3, 2);
	}

	@Test
	public void testcase1(){
		DinnerPlateStacks dps = new DinnerPlateStacks(2);
		dps.push(1);
		dps.push(2);
		dps.push(3);
		dps.push(4);
		dps.push(5);
		int n1 = dps.popAtStack(0);
		dps.push(20);
		dps.push(21);
		int n2 = dps.popAtStack(0);
		int n3 = dps.popAtStack(2);
		int n4 = dps.pop();
		int n5 = dps.pop();
		int n6 = dps.pop();
		int n7 = dps.pop();
		int n8 = dps.pop();
	}

	@Test
	public void testcase2(){
		DinnerPlateStacks dps = new DinnerPlateStacks(2);
		dps.push(1);
		dps.push(2);
		dps.push(3);
		dps.push(4);
		dps.push(7);
		int n1 = dps.popAtStack(8);
		dps.push(20);
		dps.push(21);
		int n2 = dps.popAtStack(0);
		int n3 = dps.popAtStack(2);
		int n4 = dps.pop();
		int n5 = dps.pop();
		int n6 = dps.pop();
		int n7 = dps.pop();
		int n8 = dps.pop();
	}
	/*
	["DinnerPlates","push","push","push","push","push","popAtStack","push","push","popAtStack","popAtStack","pop","pop","pop","pop","pop"]
[[2],[1],[2],[3],[4],[7],[8],[20],[21],[0],[2],[],[],[],[],[]]
		*/
}
