package org.wshuai.leetcode;

import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Created by Wei on 12/17/2019.
 * #726 https://leetcode.com/problems/number-of-atoms/
 */
public class NumberOfAtoms {
	public String countOfAtoms(String formula) {
		Stack<String> stack = new Stack<>();
		stack.push(formula);
		while(!stack.isEmpty()){
			String str = stack.pop();
			if(str.indexOf('(') == -1){
				return buildResult(str + "#");
			}
			int i = 0, l = 0, r = 0;
			while(str.charAt(i) != ')'){
				if(str.charAt(i++) == '('){
					l = i - 1;
				}
			}
			r = i;

			int k = r + 1;
			while(k < str.length() && Character.isDigit(str.charAt(k))){
				k++;
			}
			int count = k == r + 1 ? 1 : Integer.parseInt(str.substring(r + 1, k));
			String before = str.substring(0, l);
			String after = str.substring(k);
			String parsed = parseFormula(str.substring(l + 1, r) + "#", count);
			StringBuilder sb = new StringBuilder();
			stack.push(sb.append(before).append(parsed).append(after).toString());
		}
		return "fuck!";
	}

	private String buildResult(String str){
		TreeMap<String, Integer> map = new TreeMap<>();
		StringBuilder res = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < str.length(); i++){
			char c = str.charAt(i);
			if((c >= 'A' && c <= 'Z') || c == '#'){
				if(sb.length() > 0){
					String s = sb.toString();
					int j = 0;
					while(j < s.length() && !Character.isDigit(s.charAt(j))){
						j++;
					}
					String key = s.substring(0, j);
					int newCount = j == s.length() ? 1 : Integer.parseInt(s.substring(j));
					map.put(key, map.getOrDefault(key, 0) + newCount);
				}
				sb = new StringBuilder();
			}
			if(c != '#'){
				sb.append(c);
			}
		}
		for(Map.Entry<String, Integer> entry : map.entrySet()){
			res.append(entry.getKey());
			if(entry.getValue() > 1){
				res.append(entry.getValue());
			}
		}
		return res.toString();
	}

	private String parseFormula(String str, int count){
		if(count == 1){
			return str;
		}
		StringBuilder res = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < str.length(); i++){
			char c = str.charAt(i);
			if((c >= 'A' && c <= 'Z') || c == '#'){
				if(sb.length() > 0){
					String s = sb.toString();
					int j = 0;
					while(j < s.length() && !Character.isDigit(s.charAt(j))){
						j++;
					}
					res.append(s.substring(0, j));
					int newCount = (j == s.length() ? 1 : Integer.parseInt(s.substring(j))) * count;
					res.append("" + newCount);
				}
				sb = new StringBuilder();
			}
			if(c != '#'){
				sb.append(c);
			}
		}
		return res.toString();
	}
}
