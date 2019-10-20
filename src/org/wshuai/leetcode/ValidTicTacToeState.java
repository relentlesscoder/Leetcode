package org.wshuai.leetcode;

/**
 * Created by Wei on 10/20/2019.
 * #794 https://leetcode.com/problems/valid-tic-tac-toe-state/
 */
public class ValidTicTacToeState {
	public boolean validTicTacToe(String[] board) {
		int xc = 0;
		int oc = 0;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				char c = board[i].charAt(j);
				if(c == ' '){
					continue;
				}
				if(c == 'X'){
					xc++;
				}
				if(c == 'O'){
					oc++;
				}
			}
		}
		String s1 = new String(new char[]{board[0].charAt(0),
			board[1].charAt(0), board[2].charAt(0)});
		String s2 = new String(new char[]{board[0].charAt(1),
			board[1].charAt(1), board[2].charAt(1)});
		String s3 = new String(new char[]{board[0].charAt(2),
			board[1].charAt(2), board[2].charAt(2)});
		String s4 = new String(new char[]{board[0].charAt(0),
			board[1].charAt(1), board[2].charAt(2)});
		String s5 = new String(new char[]{board[0].charAt(2),
			board[1].charAt(1), board[2].charAt(0)});
		int xcount = board[0].equals("XXX") ? 1 : 0;
		xcount += board[1].equals("XXX") ? 1 : 0;
		xcount += board[2].equals("XXX") ? 1 : 0;
		xcount += s1.equals("XXX") ? 1 : 0;
		xcount += s2.equals("XXX") ? 1 : 0;
		xcount += s3.equals("XXX") ? 1 : 0;
		xcount += s4.equals("XXX") ? 1 : 0;
		xcount += s5.equals("XXX") ? 1 : 0;
		int ocount = board[0].equals("OOO") ? 1 : 0;
		ocount += board[1].equals("OOO") ? 1 : 0;
		ocount += board[2].equals("OOO") ? 1 : 0;
		ocount += s1.equals("OOO") ? 1 : 0;
		ocount += s2.equals("OOO") ? 1 : 0;
		ocount += s3.equals("OOO") ? 1 : 0;
		ocount += s4.equals("OOO") ? 1 : 0;
		ocount += s5.equals("OOO") ? 1 : 0;
		// p1 and p2 can't win at the same time
		if(xcount > 0 && ocount > 0){
			return false;
		}
		// p1/p2 can't have more than 2
		// row/column/diagonal with same values
		if(xcount > 2 || ocount > 2){
			return false;
		}
		// p1 plays first so p1 will have same
		// or 1 more move than p2
		if(oc > xc || xc - oc > 1){
			return false;
		}
		// if p1 win the game, p2 can't move any more
		if(xcount > 0 && oc == xc){
			return false;
		}
		// if p2 win the game, p1 can't move any more
		if(ocount > 0 && oc != xc){
			return false;
		}
		return true;
	}
}
