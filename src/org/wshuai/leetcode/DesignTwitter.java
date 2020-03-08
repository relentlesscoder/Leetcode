package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/25/2016.
 * #0355 https://leetcode.com/problems/design-twitter/
 */
public class DesignTwitter {
	private Map<Integer, User> map;
	private int time;

	/** Initialize your data structure here. */
	public DesignTwitter() {
		map = new HashMap<>();
		time = 0;
	}

	/** Compose a new tweet. */
	public void postTweet(int userId, int tweetId) {
		if(!map.containsKey(userId)){
			User user = new User(userId);
			map.put(userId, user);
		}
		map.get(userId).tweets.offerFirst(new Tweet(tweetId, time++));
	}

	/** Retrieve the 10 most recent tweet ids in the user's news feed.
	 * Each item in the news feed must be posted by users who the
	 * user followed or by the user herself. Tweets must be ordered
	 * from most recent to least recent. */
	public List<Integer> getNewsFeed(int userId) {
		List<Integer> res = new ArrayList<>();
		if(!map.containsKey(userId)){
			return res;
		}
		User user = map.get(userId);
		List<List<Tweet>> tweets = new ArrayList<>();
		if(user.tweets.size() > 0){
			tweets.add(user.tweets);
		}
		for(int f : user.follows){
			if(f == userId){
				continue;
			}
			if(map.get(f).tweets.size() > 0){
				tweets.add(map.get(f).tweets);
			}
		}
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
		for(int i = 0; i < tweets.size(); i++){
			Tweet t = tweets.get(i).get(0);
			pq.offer(new int[]{t.tid, t.time, i, 0});
		}
		while(pq.size() > 0){
			int[] cur = pq.poll();
			res.add(cur[0]);
			if(res.size() == 10){
				return res;
			}
			int j = cur[3] + 1;
			if(j < tweets.get(cur[2]).size()){
				Tweet t = tweets.get(cur[2]).get(j);
				pq.offer(new int[]{t.tid, t.time, cur[2], j});
			}
		}
		return res;
	}

	/** Follower follows a followee. If the operation is invalid, it should be a no-op. */
	public void follow(int followerId, int followeeId) {
		if(!map.containsKey(followerId)){
			map.put(followerId, new User(followerId));
		}
		if(!map.containsKey(followeeId)){
			map.put(followeeId, new User(followeeId));
		}
		map.get(followerId).follows.add(followeeId);
	}

	/** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
	public void unfollow(int followerId, int followeeId) {
		if(!map.containsKey(followerId) || !map.containsKey(followeeId)){
			return;
		}
		map.get(followerId).follows.remove(followeeId);
	}

	private class User{
		int uid;
		Set<Integer> follows;
		LinkedList<Tweet> tweets;

		public User(int uid){
			this.uid = uid;
			follows = new HashSet<>();
			tweets = new LinkedList<>();
		}
	}

	private class Tweet{
		int tid;
		int time;

		public Tweet(int tid, int time){
			this.tid = tid;
			this.time = time;
		}
	}
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */