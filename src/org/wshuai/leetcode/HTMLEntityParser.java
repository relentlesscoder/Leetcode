package org.wshuai.leetcode;

/**
 * Created by Wei on 04/12/2020.
 * #1410 https://leetcode.com/problems/html-entity-parser/
 */
public class HTMLEntityParser {
	// time O(n)
	public String entityParser(String text) {
		StringBuilder sb = new StringBuilder();
		char[] arr = text.toCharArray();
		int n = arr.length;
		for(int i = 0, j = 0; i < n; i++){
			if(arr[i] != '&'){
				sb.append(arr[i]);
			}else{
				j = i + 1;
				while(j < Math.min(n, i + 6) && arr[j] != ';'){
					j++;
				}
				if(j < n && arr[j] == ';'){
					char c = arr[i + 1];
					if(c == 'q'){
						sb.append("\"");
					}else if(c == 'g'){
						sb.append(">");
					}else if(c == 'l'){
						sb.append("<");
					}else if(c == 'f'){
						sb.append("/");
					}else if(c == 'a'){
						sb.append(arr[i + 2] == 'p' ? "'" : "&");
					}
					i = j;
				}else{
					sb.append('&');
				}
			}
		}
		return sb.toString();
	}
}
