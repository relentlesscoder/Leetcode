package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 9/27/19.
 * #533 https://leetcode.com/problems/lonely-pixel-ii/
 */
public class LonelyPixelII {
	public int findBlackPixel(char[][] picture, int N) {
		int r = picture.length;
		int c = picture[0].length;
		Map<Integer, List<Integer>> rows = new HashMap<>();
		Map<Integer, List<Integer>> cols = new HashMap<>();
		List<int[]> bp = new ArrayList<>();
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				if(picture[i][j] == 'B'){
					if(!rows.containsKey(i)){
						rows.put(i, new ArrayList<>());
					}
					rows.get(i).add(j);
					if(!cols.containsKey(j)){
						cols.put(j, new ArrayList<>());
					}
					cols.get(j).add(i);
					bp.add(new int[]{i, j});
				}
			}
		}
		int res = 0;
		for(int[] b: bp){
			if(rows.get(b[0]).size() != cols.get(b[1]).size() || rows.get(b[0]).size() != N){
				continue;
			}
			boolean checkRule2 = true;
			List<Integer> allRows = cols.get(b[1]);
			List<Integer> firstRow = rows.get(allRows.get(0));
			int i = 1;
			while(i < allRows.size()){
				List<Integer> currRow = rows.get(allRows.get(i));
				if(!firstRow.equals(currRow)){
					checkRule2 = false;
					break;
				}
				i++;
			}
			if(checkRule2){
				res++;
			}
		}
		return res;
	}
}
