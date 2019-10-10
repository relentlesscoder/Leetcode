package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by Wei on 11/3/16.
 * #451 https://leetcode.com/problems/sort-characters-by-frequency/
 */
public class SortCharactersByFrequency {
	public String frequencySort(String s) {
		if (s == null || s.isEmpty()) {
			return "";
		}
		char[] arr = s.toCharArray();
		Arrays.sort(arr);
		PriorityQueue<CharCount> pq = new PriorityQueue<CharCount>(new CharCountComparator());
		char val = arr[0];
		int len = arr.length;
		int count = 0;
		for (int i = 0; i < len; i++) {
			if (arr[i] != val) {
				pq.offer(new CharCount(val, count));
				val = arr[i];
				count = 0;
			}
			count++;
		}
		pq.offer(new CharCount(val, count));
		StringBuilder sb = new StringBuilder();
		while (!pq.isEmpty()) {
			CharCount cc = pq.poll();
			int cnt = cc.count;
			char chr = cc.val;
			int i = 0;
			while (i < cnt) {
				sb.append(chr);
				i++;
			}
		}
		return sb.toString();
	}
}

class CharCountComparator implements Comparator<CharCount> {
	@Override
	public int compare(CharCount x, CharCount y) {
		return y.count - x.count;
	}
}

class CharCount {
	char val;
	int count;

	public CharCount(char val, int count) {
		this.val = val;
		this.count = count;
	}
}
