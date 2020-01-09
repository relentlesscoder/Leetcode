package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 01/09/2020.
 * #0060 https://leetcode.com/problems/permutation-sequence/
 */
public class PermutationSequence {
	// time O(n), space O(n)
	// https://leetcode.com/problems/permutation-sequence/discuss/22507/%22Explain-like-I'm-five%22-Java-Solution-in-O(n)
	public String getPermutation(int n, int k) {
		List<Integer> numbers = new ArrayList<>();
		int[] factorial = new int[n + 1];
		StringBuilder sb = new StringBuilder();

		int product = 1;
		factorial[0] = 1;
		for(int i = 1; i <= n; i++){
			// create an array of factorial lookup
			// factorial[] = {1, 1, 2, 6, 24, ... n!}
			product *= i;
			factorial[i] = product;
			// create a list of numbers to get indices
			// numbers = {1, 2, 3, 4}
			numbers.add(i);
		}
		k--;
		for(int i = 1; i <= n; i++){
			int index = k / factorial[n - i];
			sb.append(numbers.remove(index));
			k -= index * factorial[n - i];
		}

		return sb.toString();
	}
}
