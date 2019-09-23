package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Wei on 8/30/2019.
 * #950 https://leetcode.com/problems/reveal-cards-in-increasing-order/
 */
public class RevealCardsInIncreasingOrder {
	public int[] deckRevealedIncreasing(int[] deck) {
		LinkedList<Integer> list = new LinkedList<>();
		Arrays.sort(deck);
		for (int i = deck.length - 1; i >= 0; i--) {
			Integer val = list.pollLast();
			if (val == null) {
				list.offer(deck[i]);
			} else {
				list.offerFirst(val);
				list.offerFirst(deck[i]);
			}
		}
		int[] res = new int[list.size()];
		int j = 0;
		for (int i : list) {
			res[j++] = i;
		}
		return res;
	}
}
