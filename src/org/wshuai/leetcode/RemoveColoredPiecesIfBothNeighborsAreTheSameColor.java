package org.wshuai.leetcode;

/**
 * Created by Wei on 10/25/2023.
 * #2038 https://leetcode.com/problems/remove-colored-pieces-if-both-neighbors-are-the-same-color/
 */
public class RemoveColoredPiecesIfBothNeighborsAreTheSameColor {

	// time O(n), space O(1)
	public boolean winnerOfGameCount1(String colors) {
		int countA = 0, countB = 0, moveA = 0, moveB = 0;
		for (int i = 0; i < colors.length(); i++) {
			if (colors.charAt(i) == 'A') {
				moveB += Math.max(0, countB - 2);
				countB = 0;
				countA++;
			} else {
				moveA += Math.max(0, countA - 2);
				countA = 0;
				countB++;
			}
		}
		moveB += Math.max(0, countB - 2);
		moveA += Math.max(0, countA - 2);
		return moveA > moveB;
	}

	// time O(n), space O(1)
	public boolean winnerOfGameCount2(String colors) {
		int moveA = 0, moveB = 0;
		for (int i = 1; i < colors.length() - 1; i++) {
			if (colors.charAt(i) == 'A' && colors.charAt(i - 1) == 'A' && colors.charAt(i + 1) == 'A') {
				moveA++;
			} else if (colors.charAt(i) == 'B' && colors.charAt(i - 1) == 'B' && colors.charAt(i + 1) == 'B') {
				moveB++;
			}
		}
		return moveA > moveB;
	}
}
