package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 12/13/2019.
 * #0591 https://leetcode.com/problems/tag-validator/
 */
public class TagValidator {
	public boolean isValid(String code) {
		if(code == null || code.length() == 0){
			return false;
		}
		char[] arr = code.toCharArray();
		int n = arr.length;
		Stack<String> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		int i = 0;
		String startTag = "###";
		String endTag = "$$$";
		while(i < n){
			if(arr[i] == '<'){
				if(i >= n - 1){
					return false;
				}
				//CDATA
				int j;
				if(arr[i + 1] == '!'){
					j = i;
					sb = new StringBuilder();
					while(j < n && !sb.toString().endsWith("]]>")){
						sb.append(arr[j++]);
					}
					if(!isValidCharacterData(sb.toString())){
						return false;
					}
				}else if(arr[i + 1] == '/'){
					j = i + 2;
					sb = new StringBuilder();
					while(j < n && arr[j] != '>'){
						sb.append(arr[j++]);
					}
					j++;
					String tag = sb.toString();
					if(!isValidEndTag(tag, stack)){
						return false;
					}
					if(j == n){
						endTag = tag;
					}
				}else{
					j = i + 1;
					sb = new StringBuilder();
					while(j < n && arr[j] != '>'){
						sb.append(arr[j++]);
					}
					j++;
					String tag = sb.toString();
					if(!isValidStartTag(tag, stack)){
						return false;
					}
					if(i == 0){
						startTag = tag;
					}
				}
				i = j;
			}else{
				i++;
			}
		}
		return stack.isEmpty() && startTag.equals(endTag);
	}

	private boolean isValidCharacterData(String tag){
		return tag.startsWith("<![CDATA[");
	}

	private boolean isValidEndTag(String tag, Stack<String> stack){
		if(!isValidTag(tag)){
			return false;
		}
		if(stack.isEmpty() || !tag.equals(stack.peek())){
			return false;
		}
		stack.pop();
		return true;
	}

	private boolean isValidStartTag(String tag, Stack<String> stack){
		if(!isValidTag(tag)){
			return false;
		}
		stack.push(tag);
		return true;
	}

	private boolean isValidTag(String tag){
		if(tag.length() < 1 || tag.length() > 9){
			return false;
		}
		for(int i = 0; i < tag.length(); i++){
			char c = tag.charAt(i);
			if(c < 'A' || c > 'Z'){
				return false;
			}
		}
		return true;
	}
}
