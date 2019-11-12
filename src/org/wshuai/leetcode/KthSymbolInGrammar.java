package org.wshuai.leetcode;

/**
 * Created by Wei on 11/11/2019.
 * #779 https://leetcode.com/problems/k-th-symbol-in-grammar/
 */
public class KthSymbolInGrammar {
	// binary tree
	//  0    1
	// 0 1  1 0
	public int kthGrammar(int N, int K) {
		if (N == 1){
			return 0;
		}
		if (K % 2 == 0){
			return (kthGrammar(N - 1, K / 2) == 0) ? 1 : 0;
		}else{
			return (kthGrammar(N - 1, (K + 1) / 2) == 0) ? 0 : 1;
		}
	}
}
