package org.wshuai.leetcode;

/**
 * Created by Wei on 11/11/2019.
 * #0779 https://leetcode.com/problems/k-th-symbol-in-grammar/
 */
public class KthSymbolInGrammar {

	// time O(N)
	public int kthGrammar(int N, int K) {
		// draw the binary tree
		//  0    1
		// 0 1  1 0
		if(N == 1){
			return 0;
		}
		if(K % 2 == 0){
			return 1 - kthGrammar(N - 1, K >> 1);
		}else{
			return kthGrammar(N - 1, (K + 1) >> 1);
		}
	}
}
