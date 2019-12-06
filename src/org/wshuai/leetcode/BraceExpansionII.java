package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

/**
 * Created by Wei on 12/5/2019.
 * #1096 https://leetcode.com/problems/brace-expansion-ii/
 */
public class BraceExpansionII {
	private TreeSet<String> set;
	Stack<String> stack;

	public List<String> braceExpansionII(String expression) {
		set = new TreeSet<>();
		stack = new Stack<>();
		stack.push(expression);
		dfs();
		return new ArrayList<>(set);
	}

	private void dfs(){
		while(!stack.isEmpty()){
			String str = stack.pop();
			if(str.indexOf('{') == -1){
				set.add(str);
				continue;
			}

			int i = 0, l = 0, r = 0;
			while(str.charAt(i) != '}'){
				if(str.charAt(i++) == '{'){
					l = i - 1;
				}
			}
			r = i;

			String before = str.substring(0, l);
			String after = str.substring(r + 1, str.length());
			String[] options = str.substring(l + 1, r).split(",");

			for(String s : options){
				StringBuilder sb = new StringBuilder();
				stack.push(sb.append(before).append(s).append(after).toString());
			}
		}
	}
}
