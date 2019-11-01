package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by Wei on 11/1/2019.
 * #1081 https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
 */
public class SmallestSubsequenceOfDistinctCharacters {
	public String smallestSubsequence(String text) {
		char[] arr = text.toCharArray();
		int[] last = new int[26];
		boolean[] used = new boolean[26];
		Arrays.fill(last, -1);
		for(int i = 0; i < arr.length; i++){
			last[arr[i] - 'a'] = i;
		}
		Stack<Integer> stack = new Stack<>();
		for(int i = 0; i < arr.length; i++){
			int val = arr[i] - 'a';
			if(used[val]){
				continue;
			}
			while(!stack.isEmpty() && stack.peek() > val && i < last[stack.peek()]){
				used[stack.pop()] = false;
			}
			stack.push(val);
			used[val] = true;
		}
		StringBuilder sb = new StringBuilder();
		for(int v: stack){
			sb.append((char)(v + 'a'));
		}
		return sb.toString();
	}
}
