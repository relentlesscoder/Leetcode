package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/5/2019.
 * #842 https://leetcode.com/problems/split-array-into-fibonacci-sequence/
 */
public class SplitArrayIntoFibonacciSequence {
	public List<Integer> splitIntoFibonacci(String S) {
		for(int i = 1; i < S.length(); i++){
			List<Long> temp = new ArrayList<>();
			String val1 = S.substring(0, i);
			if(val1.startsWith("0") && val1.length() > 1){
				break;
			}
			long first = Long.parseLong(val1);
			if(first > Integer.MAX_VALUE){
				break;
			}
			temp.add(first);
			String T = S.substring(i);
			for(int j = 1; j < T.length(); j++){
				String val2 = T.substring(0, j);
				if(val2.startsWith("0") && val2.length() > 1){
					break;
				}
				long second = Long.parseLong(val2);
				if(second > Integer.MAX_VALUE){
					break;
				}
				temp.add(second);
				if(dfs(T.substring(j), temp)){
					List<Integer> res = new ArrayList<>();
					for(long l : temp){
						res.add((int)l);
					}
					return res;
				}else{
					temp = new ArrayList<>();
					temp.add(first);
				}
			}
		}
		return new ArrayList<>();
	}

	private boolean dfs(String S, List<Long> list){
		if(S.equals("")){
			return true;
		}
		long first = list.get(list.size() - 1);
		long second = list.get(list.size() - 2);
		long next = first + second;
		if(next > Integer.MAX_VALUE){
			return false;
		}
		String val = Long.toString(next);
		if(S.startsWith(val)){
			list.add(next);
			if(dfs(S.substring(val.length()), list)){
				return true;
			}
		}
		return false;
	}
}
