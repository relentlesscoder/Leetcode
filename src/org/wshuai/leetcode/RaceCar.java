package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Wei on 12/02/2019.
 * #0818 https://leetcode.com/problems/race-car/
 */
public class RaceCar {

	public int racecar(int target) {
		int seqence = 0;
		LinkedList<int[]> queue = new LinkedList<>();
		queue.offerLast(new int[]{0, 1});
		Set<String> visited = new HashSet<>();
		visited.add("0,1");
		while(!queue.isEmpty()){
			int size = queue.size();
			while(size-- > 0){
				int[] cur = queue.pollFirst();
				int[] a = new int[]{cur[0] + cur[1], cur[1] << 1};
				int[] r = new int[]{cur[0], cur[1] > 0 ? -1 : 1};
				if(a[0] == target){
					return seqence + 1;
				}
				if(visited.add(a[0] + "," + a[1]) && a[0] > 0 && a[0] < (target << 1)){
					queue.offerLast(a);
				}
				if(visited.add(r[0] + "," + r[1]) && r[0] > 0 && r[0] < (target << 1)){
					queue.offerLast(r);
				}
			}
			seqence++;
		}
		return -1;
	}
}
