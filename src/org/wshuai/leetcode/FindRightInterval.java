package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Wei on 11/12/16.
 * #436 https://leetcode.com/problems/find-right-interval/
 */
public class FindRightInterval {
	//O(n*lg(n)) binary search
	public int[] findRightInterval(Interval[] intervals) {
		if (intervals == null || intervals.length == 0) {
			return new int[0];
		}
		int len = intervals.length;
		Pair[] aux = new Pair[len];
		int k = 0;
		for (Interval i : intervals) {
			aux[k] = new Pair(i.start, k);
			k++;
		}
		Arrays.sort(aux, new PairComparator());
		int[] res = new int[len];
		for (int i = 0; i < len; i++) {
			Interval in = intervals[i];
			int end = in.end;
			int min = searchIn(end, aux, len);
			res[i] = min;
		}
		return res;
	}

	private int searchIn(int end, Pair[] aux, int len) {
		int i = 0;
		int j = len - 1;
		while (i <= j) {
			int mid = i + (j - i) / 2;
			Pair p = aux[mid];
			if (p.key >= end) {
				if (mid == 0 || aux[mid - 1].key < end) {
					return p.val;
				} else {
					j = mid - 1;
				}
			} else {
				i = mid + 1;
			}
		}

		return -1;
	}
}

class Pair {
	int key;
	int val;

	public Pair(int key, int val) {
		this.key = key;
		this.val = val;
	}
}

class PairComparator implements Comparator<Pair> {
	@Override
	public int compare(Pair x, Pair y) {
		return x.key - y.key;
	}
}
