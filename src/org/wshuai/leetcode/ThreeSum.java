package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 8/14/2016.
 * #15 https://leetcode.com/problems/3sum/
 */
public class ThreeSum {

	//O(n^2)
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> lst = new ArrayList<List<Integer>>();
		if (nums == null || nums.length == 0) {
			return lst;
		}

		int len = nums.length;
		Arrays.sort(nums);
		int i = 0;
		while (i < len - 2) {
			int val = nums[i];
			int left = i + 1;
			int right = len - 1;
			while (left < right) {
				int lVal = nums[left];
				int rVal = nums[right];
				int sum = nums[i] + lVal + rVal;
				if (sum == 0) {
					List<Integer> ls = new ArrayList<Integer>();
					ls.add(nums[i]);
					ls.add(nums[left]);
					ls.add(nums[right]);
					lst.add(ls);
					while (left < right && nums[left] == lVal) {
						left++;
					}
					while (left < right && nums[right] == rVal) {
						right--;
					}
				} else if (sum > 0) {
					right--;
				} else {
					left++;
				}
			}
			while (i < len - 2 && nums[i] == val) {
				i++;
			}
		}
		return lst;
	}

	// Time complexity O(n^2) TLE
	public static List<List<Integer>> threeSumHashMap(int[] nums) {
		if (nums == null) {
			throw new IllegalArgumentException("Invalid input.");
		}
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		int len = nums.length;
		if (len < 3) {
			return result;
		}
		Map<Integer, List<int[]>> hmap = new HashMap<Integer, List<int[]>>();
		for (int i = 0; i < len - 1; i++) {
			for (int j = i + 1; j < len; j++) {
				int[] arr = new int[2];
				arr[0] = i;
				arr[1] = j;
				int sum = nums[i] + nums[j];
				if (hmap.containsKey(sum)) {
					List<int[]> lst = hmap.get(sum);
					lst.add(arr);
				} else {
					List<int[]> lst = new ArrayList<int[]>();
					lst.add(arr);
					hmap.put(sum, lst);
				}
			}
		}
		for (int x = 0; x < len; x++) {
			int diff = 0 - nums[x];
			if (hmap.containsKey(diff)) {
				List<int[]> lstPos = hmap.get(diff);
				for (int[] arrPos : lstPos) {
					if (x == arrPos[0] || x == arrPos[1]) {
						continue;
					}

					int first = nums[arrPos[0]];
					int second = nums[arrPos[1]];
					List<Integer> l = new ArrayList<Integer>();
					int minij = first < second ? first : second;
					int maxij = first >= second ? first : second;
					if (nums[x] < minij) {
						l.add(nums[x]);
						l.add(minij);
						l.add(maxij);
					} else if (nums[x] >= minij && nums[x] <= maxij) {
						l.add(minij);
						l.add(nums[x]);
						l.add(maxij);
					} else {
						l.add(minij);
						l.add(maxij);
						l.add(nums[x]);
					}
					boolean added = false;
					for (List<Integer> lst : result) {
						if (lst.get(0) == l.get(0) && lst.get(1) == l.get(1) && lst.get(2) == l.get(2)) {
							added = true;
							break;
						}
					}
					if (!added) {
						result.add(l);
					}
				}
			}
		}

		return result;
	}

	// Time complexity O(n^3) TLE
	public static List<List<Integer>> threeSumBrutalForce(int[] nums) {
		if (nums == null) {
			throw new IllegalArgumentException("Invalid input.");
		}
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		int len = nums.length;
		if (len < 3) {
			return result;
		}
		for (int i = 0; i < len - 2; i++) {
			for (int j = i + 1; j < len - 1; j++) {
				for (int x = j + 1; x < len; x++) {
					if (nums[i] + nums[j] + nums[x] == 0) {
						List<Integer> l = new ArrayList<Integer>();
						int minij = nums[i] < nums[j] ? nums[i] : nums[j];
						int maxij = nums[i] >= nums[j] ? nums[i] : nums[j];
						if (nums[x] < minij) {
							l.add(nums[x]);
							l.add(minij);
							l.add(maxij);
						} else if (nums[x] >= minij && nums[x] <= maxij) {
							l.add(minij);
							l.add(nums[x]);
							l.add(maxij);
						} else {
							l.add(minij);
							l.add(maxij);
							l.add(nums[x]);
						}
						boolean added = false;
						for (List<Integer> lst : result) {
							if (lst.get(0) == l.get(0) && lst.get(1) == l.get(1) && lst.get(2) == l.get(2)) {
								added = true;
								break;
							}
						}
						if (!added) {
							result.add(l);
						}
					}
				}
			}
		}

		return result;
	}
}
