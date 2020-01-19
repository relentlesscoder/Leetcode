package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 01/05/2020.
 * #1311 https://leetcode.com/problems/get-watched-videos-by-your-friends/
 */
public class GetWatchedVideosByYourFriends {
	public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
		int n = friends.length;
		int curLevel = 0;
		LinkedList<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[n];
		queue.offerLast(id);
		visited[id] = true;
		while(!queue.isEmpty()){
			int size = queue.size();
			while(size > 0){
				int next = queue.pollFirst();
				for(int f : friends[next]){
					if(!visited[f]){
						visited[f] = true;
						queue.offerLast(f);
					}
				}
				size--;
			}
			curLevel++;
			if(curLevel == level){
				break;
			}
		}
		Map<String, Integer> map = new HashMap<>();
		while(!queue.isEmpty()){
			int cur = queue.pollFirst();
			for(String video : watchedVideos.get(cur)){
				map.put(video, map.getOrDefault(video, 0) + 1);
			}
		}
		TreeSet<VideoFreq> ts = new TreeSet<>((a, b) -> a.freq == b.freq ?
			a.id.compareTo(b.id) : a.freq - b.freq);
		for(Map.Entry<String, Integer> entry : map.entrySet()){
			ts.add(new VideoFreq(entry.getKey(), entry.getValue()));
		}
		List<String> res = new ArrayList<>();
		for(VideoFreq vf : ts){
			res.add(vf.id);
		}
		return res;
	}
}

class VideoFreq{
	String id;
	int freq;

	public VideoFreq(String id, int freq){
		this.id = id;
		this.freq = freq;
	}
}
