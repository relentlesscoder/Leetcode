package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/25/2023.
 * #2049 https://leetcode.com/problems/count-nodes-with-the-highest-score/
 */
public class CountNodesWithTheHighestScore {

	// time O(n), space O(n)
	public int countHighestScoreNodes(int[] parents) {
		int n = parents.length;
		int[] left = new int[n], right = new int[n];
		long[] score = new long[n];
		// build the tree
		Arrays.fill(left, -1);
		Arrays.fill(right, -1);
		for (int i = 0; i < n; i++) {
			if (parents[i] != -1) {
				if (left[parents[i]] == -1) {
					left[parents[i]] = i;
				} else {
					right[parents[i]] = i;
				}
			}
		}
		// recursively calculate the score of removing each node
		dfs(0, left, right, n, score);
		// find the count of nodes that has max score
		long max = 0L;
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (score[i] > max) {
				max = score[i];
				count = 1;
			} else if (score[i] == max) {
				count++;
			}
		}
		return count;
	}

	private int dfs(int node, int[] left, int[] right, long n, long[] score) {
		if (node == -1) {
			return 0;
		}
		int sizeOfLeftSubtree = dfs(left[node], left, right, n, score);
		int sizeOfrightSubtree = dfs(right[node], left, right, n, score);
		long product = 1L, sizeOfTheRest = n - 1L - sizeOfLeftSubtree - sizeOfrightSubtree;
		if (sizeOfTheRest != 0) {
			product *= sizeOfTheRest;
		}
		if (sizeOfLeftSubtree != 0) {
			product *= sizeOfLeftSubtree;
		}
		if (sizeOfrightSubtree != 0) {
			product *= sizeOfrightSubtree;
		}
		score[node] = product;
		return sizeOfLeftSubtree + sizeOfrightSubtree + 1;
	}
}
