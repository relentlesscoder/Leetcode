package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 9/21/2016.
 */
public class GroupShiftedStrings {
	public List<List<String>> groupStrings(String[] strings) {
		List<List<String>> lst = new ArrayList<List<String>>();
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		if (strings == null || strings.length == 0) {
			return lst;
		}
		int len = strings.length;
		for (int i = 0; i < len; i++) {
			String val = strings[i];
			String key = getKeyValue(val);
			if (map.containsKey(key)) {
				List<String> ls = map.get(key);
				ls.add(val);
			} else {
				List<String> ls = new ArrayList<String>();
				ls.add(val);
				map.put(key, ls);
			}
		}

		for (List<String> ls : map.values()) {
			lst.add(ls);
		}
		return lst;
	}

	private String getKeyValue(String val) {
		char[] chs = val.toCharArray();
		if (chs[0] == 'a') {
			return val;
		} else {
			int diff = chs[0] - 'a';
			int len = chs.length;
			for (int i = 0; i < len; i++) {
				int cdiff = chs[i] - diff;
				cdiff = cdiff < 97 ? cdiff + 26 : cdiff;
				chs[i] = (char) (cdiff);
			}
			return new String(chs);
		}
	}
}
