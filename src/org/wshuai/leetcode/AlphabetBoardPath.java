package org.wshuai.leetcode;

/**
 * Created by Wei on 11/1/2019.
 * #1138 https://leetcode.com/problems/alphabet-board-path/
 */
public class AlphabetBoardPath {
	public String alphabetBoardPath(String target) {
		StringBuilder res = new StringBuilder();
		char[] arr = ("a" + target).toCharArray();
		for(int i = 1; i < arr.length; i++){
			int[] p1 = boardPosition(arr[i - 1]);
			int[] p2 = boardPosition(arr[i]);
			if(p1[0] == p2[0] && p1[1] == p2[1]){
				res.append("!");
				continue;
			}
			if(p1[0] == 5){
				upDown(res, p1[0], p2[0]);
				LeftRight(res, p1[1], p2[1]);
			}else{
				LeftRight(res, p1[1], p2[1]);
				upDown(res, p1[0], p2[0]);
			}
			res.append("!");
		}
		return res.toString();
	}

	private int[] boardPosition(char c){
		int i = (int)(c - 'a');
		return new int[]{i / 5, i % 5};
	}

	private void LeftRight(StringBuilder sb, int y1, int y2){
		if(y1 != y2){
			int steps = Math.abs(y1 - y2);
			while(steps-- > 0){
				sb.append(y1 > y2 ? "L" : "R");
			}
		}
	}

	private void upDown(StringBuilder sb, int x1, int x2){
		if(x1 != x2){
			int steps = Math.abs(x1 - x2);
			while(steps-- > 0){
				sb.append(x1 > x2 ? "U" : "D");
			}
		}
	}
}
