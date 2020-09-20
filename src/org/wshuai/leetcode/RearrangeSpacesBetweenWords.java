package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 09/20/2020.
 * #1592 https://leetcode.com/problems/rearrange-spaces-between-words/
 */
public class RearrangeSpacesBetweenWords {

	// time O(n), space O(n)
	public String reorderSpaces(String text) {
		List<String> strs = new ArrayList<>();
		int spaces = 0, middle = 0, end = 0, size = 0;
		StringBuilder sb = new StringBuilder();
		for(char c : text.toCharArray()){
			if(c == ' '){
				if(sb.length() > 0){
					strs.add(sb.toString());
					size++;
					sb = new StringBuilder();
				}
				spaces++;
			}else{
				sb.append(c);
			}
		}
		if(sb.length() > 0){
			strs.add(sb.toString());
			size++;
			sb = new StringBuilder();
		}
		middle = size > 1 ? spaces / (size - 1) : 0;
		end = size > 1 ? spaces % (size - 1) : spaces;
		for(int i = 0; i < size; i++){
			sb.append(strs.get(i));
			int count = i == size - 1 ? end : middle;
			while(count-- > 0){
				sb.append(" ");
			}
		}
		return sb.toString();
	}
}
