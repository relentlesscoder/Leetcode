package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 09/21/2016.
 * #0401 https://leetcode.com/problems/binary-watch/
 */
public class BinaryWatch {
	// time O(10!/n!*(10 - n)!)
	public List<String> readBinaryWatchDFS(int num) {
		List<String> res = new ArrayList<>();
		dfs(num, 0, 0, res);
		return res;
	}

	private void dfs(int num, int cur, int start, List<String> res){
		if(num == 0){
			int mask = (1 << 6) - 1, hour = cur >> 6, min = (cur & mask);
			if(hour <= 11 && min <= 59){
				res.add(hour + ":" + (min < 10 ? "0" : "") + min);
			}
			return;
		}
		if(start >= 10){
			return;
		}
		dfs(num, cur, start + 1, res);
		dfs(num - 1, cur | (1 << start), start + 1, res);
	}

	// time O(2^10)
	public List<String> readBinaryWatch(int num) {
		List<String> res = new ArrayList<String>();
		if(num < 0 || num > 8){
			return res;
		}
		int max = 1 << 10;
		int mask = (1 << 6) - 1;
		for(int i = 0; i < max; i++){
			if(Integer.bitCount(i) == num){
				int hour = i >> 6;
				int min = i & mask;
				if(hour <= 11 && min <= 59){
					res.add(hour + ":" + (min < 10 ? "0" : "") + min);
				}
			}
		}
		return res;
	}
}
