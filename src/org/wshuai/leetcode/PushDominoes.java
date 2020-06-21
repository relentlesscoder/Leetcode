package org.wshuai.leetcode;

/**
 * Created by Wei on 11/06/2019.
 * #0838 https://leetcode.com/problems/push-dominoes/
 */
public class PushDominoes {
	// time O(n)
	public String pushDominoes(String dominoes) {
		/*
		4 cases:
			L.....L -> LLLLLLL
			R.....L -> RRR.LLL
			R.....R -> RRRRRRR
			L.....R -> L.....R
		 */
		dominoes = "L" + dominoes + "R";
		StringBuilder res = new StringBuilder();
		int l = 0, r = -1;
		for(int j = 1; j < dominoes.length(); j++){
			char c = dominoes.charAt(j);
			if(c == '.'){
				continue;
			}
			if(c == 'L'){
				if(l > r){
					for(int i = l + 1; i <= j; i++){
						res.append("L");
					}
				}else{
					int count = j - r + 1;
					int rc = count / 2 - 1, lc = count / 2;
					while(rc-- > 0){
						res.append("R");
					}
					if(count % 2 != 0){
						res.append(".");
					}
					while(lc-- > 0){
						res.append("L");
					}
				}
				l = j;
			}else{
				if(l < r){
					for(int i = r + 1; i <= j; i++){
						res.append("R");
					}
				}else{
					for(int i = l + 1; i < j; i++){
						res.append(".");
					}
					res.append("R");
				}
				r = j;
			}
		}
		return res.substring(0, res.length() - 1);
	}
}
