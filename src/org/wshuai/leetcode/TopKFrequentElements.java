package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/2/2016.
 * #347 https://leetcode.com/problems/top-k-frequent-elements/
 */
public class TopKFrequentElements {
	public List<Integer> topKFrequent(int[] nums, int k) {
		List<Integer> lst = new ArrayList<Integer>();
		PriorityQueue<ValueCount> pq = new PriorityQueue<ValueCount>(new ValueCountComparator());
		Arrays.sort(nums);
		int len = nums.length;
		int val = nums[0];
		int count = 0;
		for (int i = 0; i < len; i++) {
			if (nums[i] != val) {
				pq.offer(new ValueCount(val, count));
				val = nums[i];
				count = 0;
			}
			count++;
		}
		pq.offer(new ValueCount(val, count));
		int i = 0;
		while (i < k) {
			ValueCount vc = pq.poll();
			lst.add(vc.val);
			i++;
		}
		return lst;
	}
}

class ValueCount {
	int val;
	int count;

	public ValueCount(int val, int count) {
		this.val = val;
		this.count = count;
	}
}

class ValueCountComparator implements Comparator<ValueCount> {
	@Override
	public int compare(ValueCount x, ValueCount y) {
		return y.count - x.count;
	}
}
