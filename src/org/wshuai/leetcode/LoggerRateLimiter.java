package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/18/2016.
 * #0359 https://leetcode.com/problems/logger-rate-limiter/
 */
public class LoggerRateLimiter {
	private Map<String, Integer> map;
	/** Initialize your data structure here. */
	public LoggerRateLimiter() {
		map = new HashMap<>();
	}

	/** Returns true if the message should be printed in the given timestamp, otherwise returns false.
	 If this method returns false, the message will not be printed.
	 The timestamp is in seconds granularity. */
	public boolean shouldPrintMessage(int timestamp, String message) {
		boolean res = !map.containsKey(message) || timestamp - map.get(message) >= 10;
		if(res){
			map.put(message, timestamp);
		}
		return res;
	}
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
