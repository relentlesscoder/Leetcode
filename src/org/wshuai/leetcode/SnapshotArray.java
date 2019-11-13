package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by Wei on 11/12/2019.
 * #1146 https://leetcode.com/problems/snapshot-array/
 */
public class SnapshotArray {
	private int len;
	private int sid;
	private HashMap<Integer, TreeMap<Integer, Integer>> map;

	public SnapshotArray(int length) {
		len = length;
		sid = 0;
		map = new HashMap<>();
	}

	public void set(int index, int val) {
		if(index >= len){
			return;
		}
		map.putIfAbsent(index, new TreeMap<>());
		map.get(index).put(sid, val);
	}

	public int snap() {
		sid++;
		return sid - 1;
	}

	public int get(int index, int snap_id) {
		if(!map.containsKey(index)){
			return 0;
		}
		TreeMap<Integer, Integer> tm = map.get(index);
		Integer lower = tm.floorKey(snap_id);
		if(lower == null){
			return 0;
		}
		return tm.get(lower);
	}
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */