package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/28/2023.
 * #1996 https://leetcode.com/problems/the-number-of-weak-characters-in-the-game/
 */
public class TheNumberOfWeakCharactersInTheGame {

	// time O(n + m), space O(m) m - max attack
	public int numberOfWeakCharacters(int[][] properties) {
		// https://leetcode.com/problems/the-number-of-weak-characters-in-the-game/editorial/
		int res = 0, n = properties.length, maxDefense = 0, maxAttack = 0;
		for (int[] p : properties) {
			maxAttack = Math.max(maxAttack, p[0]);
		}
		int[] maxDefenseForAttack = new int[maxAttack + 2];
		Arrays.fill(maxDefenseForAttack, 0);
		for (int i = 0; i < n; i++) {
			maxDefenseForAttack[properties[i][0]] = Math.max(maxDefenseForAttack[properties[i][0]], properties[i][1]); // store the max defense value for each attack value
		}
		for (int i = maxAttack; i >= 1; i--) { // If attack a is greater than attack b, then we can update the max defense of b to max(d(a), d(b)). After this is done we have max defense value for all attacks that are greater than attack a.
			maxDefense = Math.max(maxDefense, maxDefenseForAttack[i]);
			maxDefenseForAttack[i] = maxDefense;
		}
		for (int[] p : properties) {
			if (p[1] < maxDefenseForAttack[p[0] + 1]) {
				res++;
			}
		}
		return res;
	}

	// time O(n * log(n)), space O(1)
	public int numberOfWeakCharactersSorting(int[][] properties) {
		int res = 0, n = properties.length, maxDefense = 0;
		Arrays.sort(properties, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
		for (int i = n - 1; i >= 0; i--) {
			if (maxDefense > properties[i][1]) {
				res++;
			}
			maxDefense = Math.max(maxDefense, properties[i][1]);
		}
		return res;
	}
}
