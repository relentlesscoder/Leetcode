package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/11/2019.
 * #722 https://leetcode.com/problems/remove-comments/
 */
public class RemoveComments {
	public List<String> removeComments(String[] source) {
		List<String> res = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		boolean mode = false;
		for(String s : source){
			for(int i = 0; i < s.length(); i++){
				// processing block comment when mode is true
				if(mode){
					// closing block comment */
					if(s.charAt(i) == '*' && i < s.length() - 1 &&  s.charAt(i + 1) == '/'){
						mode = false;
						i++;
					}
				}else{
					// line comment tag, ignore the rest of the line
					if(s.charAt(i) == '/' && i < s.length() - 1 && s.charAt(i + 1) == '/'){
						break;
					// staring block comment /*
					}else if(s.charAt(i) == '/' && i < s.length() - 1 && s.charAt(i + 1) == '*'){
						mode = true;
						i++;
					}else{
						sb.append(s.charAt(i));
					}
				}
			}

			if(!mode && sb.length() > 0){
				res.add(sb.toString());
				sb = new StringBuilder();
			}
		}
		return res;
	}
}
