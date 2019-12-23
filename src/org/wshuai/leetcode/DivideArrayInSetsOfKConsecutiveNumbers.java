package org.wshuai.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Wei on 12/23/19.
 * #1296 https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
 */
public class DivideArrayInSetsOfKConsecutiveNumbers {

	public boolean isPossibleDivide(int[] nums, int k) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		int total = 0;
		Arrays.sort(nums);
		int cur = nums[0];
		int cnt = 0;
		for(int n : nums){
			total++;
			if(n == cur){
				cnt++;
			}else{
				pq.offer(new int[]{cur, cnt});
				cnt = 1;
				cur = n;
			}
		}
		pq.offer(new int[]{cur, cnt});
		if(total % k != 0){
			return false;
		}
		while(!pq.isEmpty()){
			if(pq.size() < k){
				return false;
			}
			List<int[]> toAdd = new ArrayList<>();
			for(int i = 0; i < k; i++){
				int[] next = pq.poll();
				next[1]--;
				if(toAdd.size() > 0 && next[0] != toAdd.get(toAdd.size() - 1)[0] + 1){
					return false;
				}
				toAdd.add(next);
			}
			for(int[] a : toAdd){
				if(a[1] == 0){
					continue;
				}
				pq.offer(a);
			}
		}
		return pq.size() == 0;
	}
}
