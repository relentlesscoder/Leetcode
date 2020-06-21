package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/11/2019.
 * #0722 https://leetcode.com/problems/remove-comments/
 */
public class RemoveComments {
	// time O(n)
	public List<String> removeComments(String[] source) {
		List<String> res = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		boolean isComment = false;
		for(String line : source){
			for(int i = 0; i < line.length(); i++){
				char cur = line.charAt(i);
				// processing block comment when mode is true
				if(isComment){
					// closing block comment */
					if(cur == '*' && i + 1 < line.length() && line.charAt(i + 1) == '/'){
						isComment = false;
						i++;
					}
				}else{
					// line comment tag, ignore the rest of the line
					if(cur == '/' && i + 1 < line.length() && line.charAt(i + 1) == '/'){
						break;
					}else if(cur == '/' && i + 1 < line.length() && line.charAt(i + 1) == '*'){ // staring block comment /*
						isComment = true;
						i++;
					}else{
						sb.append(cur);
					}
				}
			}
			if(!isComment && sb.length() > 0){
				res.add(sb.toString());
				sb = new StringBuilder();
			}
		}
		return res;
	}
}
