package org.wshuai.leetcode;

import java.awt.Point;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 06/28/2020.
 * #1496 https://leetcode.com/problems/path-crossing/
 */
public class PathCrossing {

	// time O(n)
	public boolean isPathCrossing(String path) {
		int x = 0, y = 0;
		Set<Integer> visited = new HashSet<>();
		visited.add(0);
		for(char c : path.toCharArray()){
			if(c == 'N'){
				x -= 1;
			}else if(c == 'S'){
				x += 1;
			}else if(c == 'W'){
				y -= 1;
			}else{
				y += 1;
			}
			if(!visited.add(x * 31 + y)){
				return true;
			}
		}
		return false;
	}


}
