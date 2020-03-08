package org.wshuai.leetcode;

/**
 * Created by Wei on 10/24/2019.
 * #0470 https://leetcode.com/problems/implement-rand10-using-rand7/
 */
public class ImplementRand10UsingRand7 {
	// time O(1)
	// https://leetcode.com/problems/implement-rand10-using-rand7/discuss/322262/Intuition-for-magic-number-40
	// https://leetcode.com/problems/implement-rand10-using-rand7/discuss/150301/Three-line-Java-solution-the-idea-can-be-generalized-to-%22Implement-RandM()-Using-RandN()%22
	public int rand10() {
		int res = 40;
		while(res >= 40){
			res = 7 * (rand7() - 1) + (rand7() - 1);
		}
		return res % 10 + 1;
	}

	// dummy implementation
	public int rand7(){
		return -1;
	}
}


