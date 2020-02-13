package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 02/13/2020.
 * #1345 https://leetcode.com/problems/jump-game-iv/
 */
public class JumpGameIV {
	public int minJumps(int[] arr) {
		int res = 0, n = arr.length;
		if (n == 1) {
			return 0;
		}
		boolean[] visited = new boolean[n];
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			map.putIfAbsent(arr[i], new ArrayList<>());
			map.get(arr[i]).add(i);
		}
		Set<Integer> head = new HashSet<>(), tail = new HashSet<>();
		head.add(0);
		tail.add(n - 1);
		visited[0] = true;
		visited[n - 1] = true;
		while (head.size() > 0 && tail.size() > 0) {
			if (head.size() > tail.size()) {
				Set<Integer> temp = tail;
				tail = head;
				head = temp;
			}
			Set<Integer> next = new HashSet<>();
			for (int i : head) {
				int x = i + 1, y = i - 1;
				if (tail.contains(x) || tail.contains(y)) {
					return res + 1;
				}
				if (x < n && !visited[x]) {
					visited[x] = true;
					next.add(x);
				}
				if (y >= 0 && !visited[y]) {
					visited[y] = true;
					next.add(y);
				}
				for (int z : map.get(arr[i])) {
					if (z == i) {
						continue;
					}
					if (tail.contains(z)) {
						return res + 1;
					}
					if (!visited[z]) {
						visited[z] = true;
						next.add(z);
					}
				}
			}
			head = next;
			res++;
		}
		return -1;
	}
}
