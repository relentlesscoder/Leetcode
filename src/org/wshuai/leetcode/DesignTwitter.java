package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/25/16.
 * #355 https://leetcode.com/problems/design-twitter/
 */
public class DesignTwitter {
	private Map<Integer, Set<Integer>> userMap;
	private List<Tweet> tweetList;

	/**
	 * Initialize your data structure here.
	 */
	public DesignTwitter() {
		userMap = new HashMap<Integer, Set<Integer>>();
		tweetList = new ArrayList<Tweet>();
	}

	/**
	 * Compose a new tweet.
	 */
	public void postTweet(int userId, int tweetId) {
		if (!userMap.containsKey(userId)) {
			Set<Integer> set = new HashSet<Integer>();
			userMap.put(userId, set);
		}
		tweetList.add(new Tweet(tweetId, userId));
	}

	/**
	 * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
	 */
	public List<Integer> getNewsFeed(int userId) {
		List<Integer> lst = new ArrayList<Integer>();
		if (!userMap.containsKey(userId)) {
			return lst;
		}
		Set<Integer> users = userMap.get(userId);
		int len = tweetList.size();
		int i = len - 1;
		int c = 0;
		while (i >= 0 && c < 10) {
			Tweet t = tweetList.get(i);
			if (users.contains(t.uId) || t.uId == userId) {
				lst.add(t.tId);
				c++;
			}
			i--;
		}
		return lst;
	}

	/**
	 * Follower follows a followee. If the operation is invalid, it should be a no-op.
	 */
	public void follow(int followerId, int followeeId) {
		if (userMap.containsKey(followerId)) {
			Set<Integer> set = userMap.get(followerId);
			set.add(followeeId);
		} else {
			Set<Integer> set = new HashSet<Integer>();
			set.add(followeeId);
			userMap.put(followerId, set);
		}
	}

	/**
	 * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
	 */
	public void unfollow(int followerId, int followeeId) {
		if (userMap.containsKey(followerId)) {
			Set<Integer> set = userMap.get(followerId);
			set.remove(followeeId);
		}
	}
}

class Tweet {

	public int tId;

	public int uId;

	public Tweet(int tId, int uId) {
		this.tId = tId;
		this.uId = uId;
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