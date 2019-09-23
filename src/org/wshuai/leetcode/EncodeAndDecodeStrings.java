package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 9/24/2016.
 */
public class EncodeAndDecodeStrings {
	// Encodes a list of strings to a single string.
	public String encode(List<String> strs) {
		if (strs == null || strs.size() == 0) {
			return "";
		}
		StringBuilder lens = new StringBuilder();
		StringBuilder vals = new StringBuilder();
		for (String str : strs) {
			lens.append(str.length() + ",");
			vals.append(str);
		}
		return lens.toString() + "#" + vals.toString();
	}

	// Decodes a single string to a list of strings.
	public List<String> decode(String s) {
		List<String> lst = new ArrayList<String>();
		if (s == null || s.isEmpty()) {
			return lst;
		}
		int idx = s.indexOf("#");
		int vidx = idx + 1;
		int lidx = 0;
		while (lidx != idx) {
			int nxt = s.indexOf(",", lidx);
			int length = Integer.parseInt(s.substring(lidx, nxt));
			lst.add(s.substring(vidx, vidx + length));
			lidx = nxt + 1;
			vidx += length;
		}
		return lst;
	}
}
