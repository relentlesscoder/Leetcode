package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Wei on 12/2/19.
 * #818 https://leetcode.com/problems/race-car/
 */
public class RaceCar {

	public int racecarBFS(int target) {
		LinkedList<int[]> queue = new LinkedList<>();
		Set<String> visited = new HashSet<>();
		queue.offerLast(new int[]{0, 1});
		visited.add(0 + "," + 1);
		int res = 0;
		while(!queue.isEmpty()){
			int size = queue.size();
			while(size-- > 0){
				int[] cur = queue.pollFirst();
				int[] n1 = new int[]{cur[0] + cur[1], cur[1] * 2};
				int[] n2 = new int[]{cur[0], cur[1] > 0 ? -1 : 1};
				if(n1[0] == target || n2[0] == target){
					return res + 1;
				}
				if(visited.add(n1[0] + "," + n1[1]) && 0 < n1[0] && n1[0] < (target << 1)){
					queue.offerLast(n1);
				}
				if(visited.add(n2[0] + "," + n2[1]) && 0 < n2[0] && n2[0] < (target << 1)){
					queue.offerLast(n2);
				}
			}
			res++;
		}
		return -1;
	}
}
