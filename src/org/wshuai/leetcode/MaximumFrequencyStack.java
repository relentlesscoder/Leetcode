package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by Wei on 12/10/2019.
 * #895 https://leetcode.com/problems/maximum-frequency-stack/
 */
public class MaximumFrequencyStack {
	PriorityQueue<MaxFreqStackElement> pq;
	Map<Integer, Integer> map;
	int index;

	public MaximumFrequencyStack() {
		index = 0;
		pq = new PriorityQueue<>((a, b) -> a.count == b.count ?
				b.index - a.index : b.count - a.count);
		map = new HashMap<>();
	}

	public void push(int x) {
		int curCount = map.getOrDefault(x, 0);
		MaxFreqStackElement e = new MaxFreqStackElement(x, curCount + 1, index);
		pq.offer(e);
		map.put(x, curCount + 1);
		index++;
	}

	public int pop() {
		if(pq.isEmpty()){
			return -1;
		}
		MaxFreqStackElement e = pq.poll();
		map.put(e.value, map.get(e.value) - 1);
		return e.value;
	}

	private class MaxFreqStackElement{
		int value;
		int count;
		int index;

		public MaxFreqStackElement(int v, int c, int i){
			value = v;
			count = c;
			index = i;
		}
	}

	/**
 	* Your FreqStack object will be instantiated and called as such:
 	* FreqStack obj = new FreqStack();
 	* obj.push(x);
 	* int param_2 = obj.pop();
 	*/
}
