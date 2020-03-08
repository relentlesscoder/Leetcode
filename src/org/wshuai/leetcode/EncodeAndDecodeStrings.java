package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 09/24/2016.
 * #0271 https://leetcode.com/problems/encode-and-decode-strings/
 */
public class EncodeAndDecodeStrings {
	// Encodes a list of strings to a single string.
	public String encode(List<String> strs) {
		StringBuilder sb = new StringBuilder();
		for(String s : strs){
			sb.append(s.length()).append(",").append(s);
		}
		return sb.toString();
	}

	// Decodes a single string to a list of strings.
	public List<String> decode(String s) {
		List<String> res = new ArrayList<>();
		int i = 0, j = 0;
		while(i < s.length()){
			j = s.indexOf(",", i);
			int len = Integer.parseInt(s.substring(i, j));
			res.add(s.substring(j + 1, j + 1 + len));
			i = j + 1 + len;
		}
		return res;
	}
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));
