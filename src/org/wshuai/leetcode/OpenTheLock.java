package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Wei on 10/25/2019.
 * #0752 https://leetcode.com/problems/open-the-lock/
 */
public class OpenTheLock {

	// time O(10^4), space O(10^4)
	// Bidirectional BFS 19 ms
	public int openLockBidirectionalBFS(String[] deadends, String target) {
		int res = 0;
		if (target.equals("0000")) {
			return res;
		}
		Set<String> visited = new HashSet<>(), invalid = new HashSet<>(), from = new HashSet<>(), to = new HashSet<>();
		for (String s : deadends) {
			if (s.equals(target) || s.equals("0000")) {
				return -1;
			}
			invalid.add(s);
		}
		visited.add("0000");
		from.add("0000");
		to.add(target);
		while (from.size() > 0 && to.size() > 0) {
			if (from.size() > to.size()) {
				Set<String> temp = from;
				from = to;
				to = temp;
			}
			Set<String> next = new HashSet<>();
			for (String cur : from) {
				char[] arr = cur.toCharArray();
				for (int i = 0; i < 4; i++) {
					char digit = arr[i];
					char up = digit == '9' ? '0' : (char) (digit + 1);
					char down = digit == '0' ? '9' : (char) (digit - 1);
					arr[i] = up;
					String upStr = new String(arr);
					if (to.contains(upStr)) {
						return res + 1;
					}
					if (!invalid.contains(upStr) && visited.add(upStr)) {
						next.add(upStr);
					}
					arr[i] = down;
					String downStr = new String(arr);
					if (to.contains(downStr)) {
						return res + 1;
					}
					if (!invalid.contains(downStr) && visited.add(downStr)) {
						next.add(downStr);
					}
					arr[i] = digit;
				}
			}
			from = next;
			res++;
		}
		return -1;
	}

	// time O(10^4), space O(10^4)
	// BFS 74 ms
	public int openLock(String[] deadends, String target) {
		int res = 0;
		LinkedList<String> queue = new LinkedList<>();
		Set<String> visited = new HashSet<>();
		Set<String> invalid = new HashSet<>();
		for (String s : deadends) {
			if (s.equals(target) || s.equals("0000")) {
				return -1;
			}
			invalid.add(s);
		}
		queue.offerLast("0000");
		visited.add("0000");
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				String cur = queue.pollFirst();
				if (cur.equals(target)) {
					return res;
				}
				char[] arr = cur.toCharArray();
				for (int i = 0; i < 4; i++) {
					char digit = arr[i];
					char up = digit == '9' ? '0' : (char) (digit + 1);
					char down = digit == '0' ? '9' : (char) (digit - 1);
					arr[i] = up;
					String upStr = new String(arr);
					if (!invalid.contains(upStr) && visited.add(upStr)) {
						queue.offerLast(upStr);
					}
					arr[i] = down;
					String downStr = new String(arr);
					if (!invalid.contains(downStr) && visited.add(downStr)) {
						queue.offerLast(downStr);
					}
					arr[i] = digit;
				}
			}
			res++;
		}
		return -1;
	}
}
