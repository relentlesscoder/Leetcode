package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 09/04/2023.
 * #2782 https://leetcode.com/problems/number-of-unique-categories/
 */
public class NumberOfUniqueCategories {

	// time O(n^2), space O(1)
	public int numberOfCategories(int n, CategoryHandler categoryHandler) {
		int res = n;
		for (int i = 0; i < n; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (categoryHandler.haveSameCategory(i, j)) {
					res--;
					break;
				}
			}
		}
		return res;
	}

	// time O(n^2 * α(n)), space O(n)
	public int numberOfCategoriesUnionFind(int n, CategoryHandler categoryHandler) {
		UnionFind uf = new UnionFind(n);
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (categoryHandler.haveSameCategory(i, j)) {
					uf.union(i, j);
				}
			}
		}
		return uf.countDisjointSet();
	}

	private class UnionFind {

		int[] root;
		int[] rank;
		int count;

		public UnionFind(int n) {
			count = n;
			root = new int[n];
			rank = new int[n];
			for (int i = 0; i < n; i++) {
				root[i] = i;
				rank[i] = 1;
			}
		}

		public int find(int x) {
			if (root[x] != x) {
				root[x] = find(root[x]);
			}
			return root[x];
		}

		public void union(int x, int y) {
			int rootX = find(x);
			int rootY = find(y);
			if (rootX == rootY) {
				return;
			}
			if (rank[rootX] > rank[rootY]) {
				root[rootY] = rootX;
				rank[rootX] += rank[rootY];
			} else {
				root[rootX] = rootY;
				rank[rootY] += rank[rootX];
			}
			count--;
		}

		public int countDisjointSet() {
			return count;
		}
	}

	// time O(n^2), space O(n^2)
	public int numberOfCategoriesDFS(int n, CategoryHandler categoryHandler) {
		int res = 0;
		List<Integer>[] adj = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (categoryHandler.haveSameCategory(i, j)) {
					adj[i].add(j);
					adj[j].add(i);
				}
			}
		}
		boolean[] visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				dfs(i, adj, visited);
				res++;
			}
		}
		return res;
	}

	private void dfs(int curr, List<Integer>[] adj, boolean[] visited) {
		visited[curr] = true;
		for (int next : adj[curr]) {
			if (!visited[next]) {
				dfs(next, adj, visited);
			}
		}
	}

	/**
	 * Definition for a category handler.
	 **/
	private class CategoryHandler {
		public CategoryHandler(int[] categories) {
		}

		public boolean haveSameCategory(int a, int b) {
			return false;
		}
	}
}
