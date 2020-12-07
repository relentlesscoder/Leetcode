package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 12/05/2020.
 * #1656 https://leetcode.com/problems/design-an-ordered-stream/
 */
public class DesignAnOrderedStream {

	private String[] stream;
	private int pointer, size;

	public DesignAnOrderedStream(int n) {
		stream = new String[n];
		pointer = 0;
		size = n;
	}

	public List<String> insert(int id, String value) {
		stream[id - 1] = value;
		List<String> res = new ArrayList<>();
		int i = pointer;
		for(; i < size && stream[i] != null; i++){
			res.add(stream[i]);
		}
		pointer = i;
		return res;
	}
}
