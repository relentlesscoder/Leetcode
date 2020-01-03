package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 12/15/2019.
 * #850 https://leetcode.com/problems/rectangle-area-ii/
 */
public class RectangleAreaII {
	// always consider line sweep first for questions about interval
	public int rectangleArea(int[][] rectangles) {
		int OPEN = 0;
		int CLOSE = 1;
		int[][] events = new int[rectangles.length * 2][];
		int t = 0;
		for(int[] rec : rectangles){
			events[t++] = new int[]{rec[1], OPEN, rec[0], rec[2]};
			events[t++] = new int[]{rec[3], CLOSE, rec[0], rec[2]};
		}
		Arrays.sort(events, (a, b) -> a[0] - b[0]);
		List<int[]> active = new ArrayList<>();
		//current y
		int cur_y = events[0][0];
		long ans = 0;
		for(int[] event : events){
			int y = event[0], type = event[1], x1 = event[2], x2 = event[3];
			long query = 0;
			int cur = -1;
			//for each horizontal edges on the same height x
			//calculate the total length. For the example, at y 0,
			//we have [0, 2], [1, 3]
			for(int[] xs : active){
				cur = Math.max(cur, xs[0]);
				query += Math.max(xs[1] - cur, 0);
				cur = Math.max(cur, xs[1]);
			}
			//calculate the area between current y and previous y and then
			//add/remove events at current y
			ans += query * (y - cur_y);
			if(type == OPEN){
				active.add(new int[]{x1, x2});
				Collections.sort(active, (a, b) -> a[0] - b[0]);
			}else{
				for(int i = 0; i < active.size(); i++){
					if(active.get(i)[0] == x1 && active.get(i)[1] == x2){
						active.remove(i);
						break;
					}
				}
			}
			//update current y
			cur_y = y;
		}
		return (int)(ans % 1_000_000_007);
	}

	public int rectangleAreaSegmentTree(int[][] rectangles) {
		int OPEN = 1;
		int CLOSE = -1;
		int[][] events = new int[rectangles.length * 2][];
		Set<Integer> xs = new HashSet<>();
		int t = 0;
		for(int[] rect : rectangles){
			events[t++] = new int[]{rect[1], OPEN, rect[0], rect[2]};
			events[t++] = new int[]{rect[3], CLOSE, rect[0], rect[2]};
			xs.add(rect[0]);
			xs.add(rect[2]);
		}

		Arrays.sort(events, (a, b) -> a[0] - b[0]);
		int[] arr = new int[xs.size()];
		int k = 0;
		for(int x : xs){
			arr[k++] = x;
		}
		Arrays.sort(arr);
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < arr.length; i++){
			map.put(arr[i], i);
		}
		SegmentTreeNodeRA active = new SegmentTreeNodeRA(0, arr.length - 1, arr);
		long ans = 0;
		long cur_x_sum = 0;
		int cur_y = events[0][0];

		for(int[] event : events){
			int y = event[0], type = event[1], x1 = event[2], x2 = event[3];
			ans += cur_x_sum * (y - cur_y);
			cur_x_sum = active.update(map.get(x1), map.get(x2), type);
			cur_y = y;
		}

		return (int)(ans % 1_000_000_007);
	}
}

class SegmentTreeNodeRA {
	public int start;
	public int end;
	public int total;
	public int[] arr;
	public int count;
	public SegmentTreeNodeRA left;
	public SegmentTreeNodeRA right;

	public SegmentTreeNodeRA(int start, int end, int[] arr) {
		this.start = start;
		this.end = end;
		this.arr = arr;
		left = null;
		right = null;
		count = 0;
		total = 0;
	}

	public long update(int i, int j, int val){
		if(i >= j){
			return 0;
		}
		if(start == i && end == j){
			count += val;
		}else{
			getLeft().update(i, Math.min(getRangeMid(), j), val);
			getRight().update(Math.max(getRangeMid(), i), j, val);
		}
		if(count > 0){
			total = arr[end] - arr[start];
		}else{
			total = getLeft().total + getRight().total;
		}
		return total;
	}

	public SegmentTreeNodeRA getLeft(){
		if(left == null){
			left = new SegmentTreeNodeRA(start, getRangeMid(), arr);
		}
		return left;
	}

	public SegmentTreeNodeRA getRight(){
		if(right == null){
			right = new SegmentTreeNodeRA(getRangeMid(), end, arr);
		}
		return right;
	}

	public int getRangeMid() {
		return start + (end - start) / 2;
	}
}
