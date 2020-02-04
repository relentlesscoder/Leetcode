package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 07/24/2017.
 * #0316 https://leetcode.com/problems/remove-duplicate-letters/
 */
public class RemoveDuplicateLetters {
	// time O(n)
	public String removeDuplicateLetters(String s) {
		if(s == null || s.isEmpty()){
			return "";
		}
		char[] arr = s.toCharArray();
		Stack<Character> stack = new Stack<>();
		int[] count = new int[26];
		boolean[] visited = new boolean[26];
		for(char c : arr){
			count[c - 'a']++;
		}
		for(char c : arr){
			int index = c - 'a';
			count[index]--;
			if(visited[index]){
				continue;
			}
			while(!stack.isEmpty() && stack.peek() > c && count[stack.peek() - 'a' ] > 0){
				visited[stack.pop() - 'a'] = false;
			}
			stack.push(c);
			visited[index] = true;
		}
		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty()){
			sb.append(stack.pop());
		}
		return sb.reverse().toString();
	}
}
