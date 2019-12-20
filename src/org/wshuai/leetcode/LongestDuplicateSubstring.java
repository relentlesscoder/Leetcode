package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/19/2019.
 * #1044 https://leetcode.com/problems/longest-duplicate-substring/
 */
public class LongestDuplicateSubstring {
	public String longestDupSubstring(String S) {
		int[] sa = buildSuffixArray(S);
		int n = sa.length;
		int[] lcp = kasai(S, sa);

		int ans = 0;
		int start = 0;
		for(int i = 0; i < n; i++){
			if(lcp[i] > ans){
				ans = lcp[i];
				start = sa[i];
			}
		}
		if(ans == 0){
			return "";
		}
		return S.substring(start, start + ans);
	}

	private int[] kasai(String s, int[] sa){
		int n = sa.length;

		int[] lcp = new int[n];
		int[] inv = new int[n];

		for(int i = 0; i < n; i++){
			inv[sa[i]] = i;
		}

		int k = 0;
		for(int i = 0; i < n; i++){
			if(inv[i] == n - 1){
				k = 0;
				continue;
			}
			int j = sa[inv[i] + 1];
			while(i + k < n && j + k < n && s.charAt(i + k) == s.charAt(j + k)){
				k++;
			}
			lcp[inv[i]] = k;
			if(k > 0){
				k--;
			}
		}
		return lcp;
	}

	private int[] buildSuffixArray(String s){
		int n = s.length();
		Suffix[] sa = new Suffix[n];

		for(int i = 0; i < n; i++){
			int cur = s.charAt(i) - 'a';
			int next = i < n - 1 ? s.charAt(i + 1) - 'a' : -1;
			sa[i] = new Suffix(i, new int[]{cur, next});
		}

		Arrays.sort(sa);
		int[] ind = new int[n];

		for(int len = 4; len < 2 * n; len <<= 1){
			int rank = 0;
			int prev = sa[0].rank[0];
			ind[sa[0].index] = 0;
			for(int i = 1; i < n; i++){
				if(sa[i].rank[0] == prev &&
					sa[i].rank[1] == sa[i - 1].rank[1]){
					prev = sa[i].rank[0];
					sa[i].rank[0] = rank;
				}else{
					prev = sa[i].rank[0];
					sa[i].rank[0] = ++rank;
				}
				ind[sa[i].index] = i;
			}

			for(int i = 0; i < n; i++){
				int nextIndex = sa[i].index + len / 2;
				sa[i].rank[1] = nextIndex < n ? sa[ind[nextIndex]].rank[0] : -1;
			}
			Arrays.sort(sa);
		}

		int[] res = new int[n];
		for(int i = 0; i < n; i++){
			res[i] = sa[i].index;
		}
		return res;
	}
}

class Suffix implements Comparable<Suffix>{
	int index;
	int[] rank;

	public Suffix(int i, int[] r){
		index = i;
		rank = r;
	}

	public int compareTo(Suffix s){
		return rank[0] == s.rank[0] ?
			rank[1] - s.rank[1] : rank[0] - s.rank[0];
	}
}
