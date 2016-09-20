package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 9/18/2016.
 */
public class LoggerRateLimiter {
  private Map<String, Integer> tMap;

  /** Initialize your data structure here. */
  public LoggerRateLimiter() {
    tMap = new HashMap<String, Integer>();
  }

  /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
   If this method returns false, the message will not be printed.
   The timestamp is in seconds granularity. */
  public boolean shouldPrintMessage(int timestamp, String message) {
    if(tMap.containsKey(message)){
      int lt = tMap.get(message);
      if(timestamp - lt >= 10){
        tMap.put(message, timestamp);
        return true;
      }
      return false;
    }else{
      tMap.put(message, timestamp);
      return true;
    }
  }
}
