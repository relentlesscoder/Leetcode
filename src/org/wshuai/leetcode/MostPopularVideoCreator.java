package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 10/03/2023.
 * #2456 https://leetcode.com/problems/most-popular-video-creator/
 */
public class MostPopularVideoCreator {

	// time O(n), space O(n)
	public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
		List<List<String>> res = new ArrayList<>();
		int n = creators.length;
		long maxViews = 0L;
		Map<String, Long> viewCount = new HashMap<>();
		Map<String, List<Integer>> videoCount = new HashMap<>();
		for (int i = 0; i < n; i++) {
			viewCount.put(creators[i], viewCount.getOrDefault(creators[i], 0L) + views[i]);
			maxViews = Math.max(maxViews, viewCount.get(creators[i]));
			videoCount.putIfAbsent(creators[i], new ArrayList<>());
			videoCount.get(creators[i]).add(i);
		}
		for (String name : viewCount.keySet()) {
			if (viewCount.get(name) == maxViews) {
				List<String> list = new ArrayList<>();
				list.add(name);
				list.add(getMostViewedVideo(videoCount.get(name), ids, views));
				res.add(list);
			}
		}
		return res;
	}

	private String getMostViewedVideo(List<Integer> indexes, String[] ids, int[] views) {
		String res = "";
		int maxView = Integer.MIN_VALUE;
		for (int i : indexes) {
			if (views[i] > maxView) {
				maxView = views[i];
				res = ids[i];
			} else if (views[i] == maxView) {
				if (res.compareTo(ids[i]) > 0) {
					res = ids[i];
				}
			}
		}
		return res;
	}
}
