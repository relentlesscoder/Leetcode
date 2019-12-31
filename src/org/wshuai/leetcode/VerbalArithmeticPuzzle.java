package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 12/30/2019.
 * #1307 https://leetcode.com/problems/verbal-arithmetic-puzzle/
 */
public class VerbalArithmeticPuzzle {
	private int[] map;
	private boolean[] used;
	private Set<Character> init;

	public boolean isSolvable(String[] words, String result) {
		map = new int[26];
		used = new boolean[10];
		init = new HashSet<Character>();
		Arrays.fill(map, -1);
		Set<Character> chars = new HashSet<>();
		for(String w : words){
			for(int i = 0; i < w.length(); i++){
				if(i == 0){
					init.add(w.charAt(i));
				}
				chars.add(w.charAt(i));
			}
		}
		for(int i = 0; i < result.length(); i++){
			if(i == 0){
				init.add(result.charAt(i));
			}
			chars.add(result.charAt(i));
		}
		return dfs(0, new ArrayList<>(chars), words, result);
	}

	private boolean dfs(int start, List<Character> chars, String[] words, String result){
		if(start == chars.size()){
			return validateExpression(words, result);
		}
		for(int i = 0; i < 10; i++){
			if(used[i]){
				continue;
			}
			Character cur = chars.get(start);
			if(i == 0 && init.contains(cur)){
				continue;
			}
			map[cur - 'A'] = i;
			used[i] = true;
			if(dfs(start + 1, chars, words, result)){
				return true;
			}
			used[i] = false;
			map[cur - 'A'] = -1;
		}
		return false;
	}

	private boolean validateExpression(String[] words, String result){
		int right = parse(result);
		int left = 0;
		for(String w : words){
			left += parse(w);
			if(left > right){
				return false;
			}
		}
		return left == right;
	}

	private int parse(String str){
		int res = 0;
		for(int i = 0; i < str.length(); i++){
			res *= 10;
			res += map[str.charAt(i) - 'A'];
		}
		return res;
	}
}
