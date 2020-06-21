package org.wshuai.leetcode;

/**
 * Created by Wei on 12/05/2019.
 * #0829 https://leetcode.com/problems/consecutive-numbers-sum/
 */
public class ConsecutiveNumbersSum {

	// time O(log(n))
	// https://leetcode.com/problems/consecutive-numbers-sum/discuss/129015/5-lines-C%2B%2B-solution-with-detailed-mathematical-explanation.
	public int consecutiveNumbersSum(int N) {
		/*The thought process goes like this. Given a number N,
		we can possibly write it as a sum of 2 numbers, 3 numbers,
		4 numbers and so on. Let's assume the fist number in this
		series be x. Hence, we should have

			x + (x+1) + (x+2)+...+ k terms = N
			kx + k*(k-1)/2 = N implies
			kx = N - k*(k-1)/2

		So, we can calculate the RHS for every value of k and if it
		is a multiple of k then we can construct a sum of N using k
		terms starting from x.
		Now, the question arises, till what value of k should we loop
		for? That's easy. In the worst case, RHS should be greater
		than 0. That is

			N - k*(k-1)/2 > 0 which implies
			k*(k-1) < 2N which can be approximated to
			k*k < 2N ==> k < sqrt(2N)

		Hence the overall complexity of the algorithm is O(sqrt(N))*/
		int count = 1;
		for(int k = 2; k < Math.sqrt(2 * N); k++){
			if(( N - ( k * ( k - 1 )/2) ) % k == 0){
				count++;
			}
		}
		return count;
	}
}
