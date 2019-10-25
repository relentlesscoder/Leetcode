package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/25/2019.
 * #990 https://leetcode.com/problems/satisfiability-of-equality-equations/
 */
public class SatisfiabilityOfEqualityEquations {
	private char[] root;

	// union find
	public boolean equationsPossible(String[] equations) {
		root = new char[26];
		for(int i = 0; i < 26; i++){
			root[i] = (char)(i + 'a');
		}
		List<char[]> uneq = new ArrayList<>();
		for(String eq: equations){
			char op = eq.charAt(1);
			char c1 = eq.charAt(0);
			char c2 = eq.charAt(3);
			if(op == '!'){
				uneq.add(new char[]{c1, c2});
				continue;
			}
			char r1 = findRoot(c1);
			char r2 = findRoot(c2);
			if(op == '='){
				root[r2 - 'a'] = r1;
			}
		}
		for(char[] u: uneq){
			char r1 = findRoot(u[0]);
			char r2 = findRoot(u[1]);
			if(r1 == r2){
				return false;
			}
		}
		return true;
	}

	private char findRoot(char c){
		if(root[c - 'a'] != c){
			root[c - 'a'] = findRoot(root[c - 'a']);
		}
		return root[c - 'a'];
	}
}
