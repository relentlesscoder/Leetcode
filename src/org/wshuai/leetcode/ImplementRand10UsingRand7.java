package org.wshuai.leetcode;

/**
 * Created by Wei on 10/24/2019.
 * #470 https://leetcode.com/problems/implement-rand10-using-rand7/
 */
public class ImplementRand10UsingRand7 {
	public int rand10() {
		int row, col, idx;
		do{
			row = rand7();
			col = rand7();
			idx = col + (row - 1) * 7;
		}while (idx > 40);
		return 1 + (idx - 1) % 10;
	}

	// dummy implementation
	public int rand7(){
		return -1;
	}
}


