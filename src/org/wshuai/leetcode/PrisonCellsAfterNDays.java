package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 10/29/19.
 * #957 https://leetcode.com/problems/prison-cells-after-n-days/
 */
public class PrisonCellsAfterNDays {
	// bs question :(
	public int[] prisonAfterNDays(int[] cells, int N) {
		if(cells == null || cells.length == 0 || N <= 0){
			return cells;
		}
		boolean cyclic = false;
		int cycle = 0;
		Set<String> set = new HashSet<>();
		for(int i = 0; i < N; i++){
			int[] next = nextDay(cells);
			String key = Arrays.toString(next);
			if(set.contains(key)){
				cyclic = true;
				break;
			}
			set.add(key);
			cycle++;
			cells = next;
		}
		if(cyclic){
			N %= cycle;
			for(int i = 0; i < N; i++){
				cells = nextDay(cells);
			}
		}
		return cells;
	}

	private int[] nextDay(int[] cells){
		int[] temp = new int[cells.length];
		for(int i = 1; i < cells.length - 1; i++){
			temp[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
		}
		return temp;
	}
}
