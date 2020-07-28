package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 07/28/2020.
 * #1505 https://leetcode.com/problems/minimum-possible-integer-after-at-most-k-adjacent-swaps-on-digits/
 */
public class MinimumPossibleIntegerAfterAtMostKAdjacentSwapsOnDigits {

	// time n*(log(n)), space O(n)
	public String minInteger(String num, int k) {
		int n = num.length(), p = (int)Math.ceil(Math.log(n)/Math.log(2));
		LinkedList<Integer>[] queue = new LinkedList[10];
		for(int i = 0; i <= 9; i++){
			queue[i] = new LinkedList<>();
		}
		for(int i = 0; i < n; i++){
			queue[num.charAt(i) - '0'].offerLast(i);
		}
		String res = "";
		int[] segmentTree = new int[2 * (int)Math.pow(2, p) - 1];
		for(int i = 0; i < n; i++){
			for(int j = 0; j <= 9; j++){
				if(queue[j].size() == 0){
					continue;
				}
				int index = queue[j].peekFirst();
				int offset = query(segmentTree, index, n);
				if(index - offset <= k){
					k -= index - offset;
					res += j;
					update(segmentTree, index, n);
					queue[j].pollFirst();
					break;
				}
			}
		}
		return res;
	}

	private int query(int[] st, int value, int n){
		return querySegmentTree(st, 0, value, 0, n - 1, 0);
	}

	private void update(int[] st, int value, int n){
		updateSegmentTree(st, value, 1, 0, n - 1, 0);
	}

	private int querySegmentTree(int[] st, int ql, int qr, int l, int r, int n){
		if(ql > r || qr < l){
			return 0;
		}
		if(ql <= l && qr >= r){
			return st[n];
		}
		int m = l + (r - l) / 2;
		return querySegmentTree(st, ql, qr, l, m, n * 2 + 1)
			+ querySegmentTree(st, ql, qr, m + 1, r, n * 2 + 2);
	}

	private void updateSegmentTree(int[] st, int i, int d, int l, int r, int n){
		if(i < l || i > r){
			return;
		}
		st[n] += d;
		if(l != r){
			int m = l + (r - l) / 2;
			updateSegmentTree(st, i, d, l, m, n * 2 + 1);
			updateSegmentTree(st, i, d, m + 1, r, n * 2 + 2);
		}
	}
}
