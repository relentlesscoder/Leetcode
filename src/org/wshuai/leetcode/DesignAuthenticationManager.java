package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Wei on 12/02/2023.
 * #1797 https://leetcode.com/problems/design-authentication-manager/
 */
public class DesignAuthenticationManager {

    private class AuthenticationManager {

        private int timeToLive;
        private Map<String, Integer> tokens;
        private TreeMap<Integer, String> times;

        public AuthenticationManager(int timeToLive) {
            this.timeToLive = timeToLive;
            tokens = new HashMap<>();
            times = new TreeMap<>();
        }

        // time O(log(n))
        public void generate(String tokenId, int currentTime) {
            int expiryTime = currentTime + timeToLive;
            tokens.put(tokenId, expiryTime);
            times.put(expiryTime, tokenId);
        }

        // time O(log(n))
        public void renew(String tokenId, int currentTime) {
            if (tokens.containsKey(tokenId) && tokens.get(tokenId) > currentTime) {
                int expiryTime = currentTime + timeToLive, prevExpiryTime = tokens.get(tokenId);
                tokens.put(tokenId, expiryTime);
                times.remove(prevExpiryTime);
                times.put(expiryTime, tokenId);
            }
        }

        // time O(log(n))
        public int countUnexpiredTokens(int currentTime) {
            return times.tailMap(currentTime, false).size();
        }
    }

/**
 * Your AuthenticationManager object will be instantiated and called as such:
 * AuthenticationManager obj = new AuthenticationManager(timeToLive);
 * obj.generate(tokenId,currentTime);
 * obj.renew(tokenId,currentTime);
 * int param_3 = obj.countUnexpiredTokens(currentTime);
 */
}
