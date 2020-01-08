package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #38 https://leetcode.com/problems/count-and-say/
 */
public class CountAndSay {
	// time ()
	public String countAndSay(int n) {
		String res = "1";
		int i = 1;
		StringBuilder sb = new StringBuilder();
		while(i < n){
			sb = new StringBuilder();
			String val = res + "#";
			char cur = res.charAt(0);
			int count = 0;
			for(char c : val.toCharArray()){
				if(c == cur){
					count++;
				}else{
					sb.append(count).append(cur);
					cur = c;
					count = 1;
				}
			}
			res = sb.toString();
			i++;
		}
		return res;
	}
}
