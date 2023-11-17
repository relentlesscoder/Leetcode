package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by Wei on 11/16/2023.
 * #2254 https://leetcode.com/problems/design-video-sharing-platform/
 */
public class DesignVideoSharingPlatform {

    // time O(n * log(n)), space O(n)
    private class VideoSharingPlatform {

        private int minId;

        private PriorityQueue<Integer> removed;

        private Map<Integer, String> videoMap;

        private Map<Integer, int[]> videoStats;

        public VideoSharingPlatform() {
            minId = 0;
            removed = new PriorityQueue<>();
            videoMap = new HashMap<>();
            videoStats = new HashMap<>();
        }

        // time O(log(n))
        public int upload(String video) {
            int videoId;
            if (!removed.isEmpty()) { // recycle previous deleted video ids
                videoId = removed.poll();
            } else {
                videoId = minId++;
            }
            videoMap.put(videoId, video);
            videoStats.put(videoId, new int[]{0, 0, 0});
            return videoId;
        }

        // time O(log(n))
        public void remove(int videoId) {
            if (videoMap.containsKey(videoId)) {
                videoMap.remove(videoId);
                videoStats.remove(videoId);
                removed.offer(videoId);
            }
        }

        // time O(l)
        public String watch(int videoId, int startMinute, int endMinute) {
            if (videoMap.containsKey(videoId)) {
                String video = videoMap.get(videoId);
                videoStats.get(videoId)[0]++;
                return video.substring(startMinute, Math.min(endMinute + 1, video.length()));
            }
            return "-1";
        }

        // time O(1)
        public void like(int videoId) {
            if (videoMap.containsKey(videoId)) {
                videoStats.get(videoId)[1]++;
            }
        }

        // time O(1)
        public void dislike(int videoId) {
            if (videoMap.containsKey(videoId)) {
                videoStats.get(videoId)[2]++;
            }
        }

        // time O(1)
        public int[] getLikesAndDislikes(int videoId) {
            if (videoMap.containsKey(videoId)) {
                int[] stat = videoStats.get(videoId);
                return new int[]{stat[1], stat[2]};
            }
            return new int[]{-1};
        }

        // time O(1)
        public int getViews(int videoId) {
            if (videoMap.containsKey(videoId)) {
                return videoStats.get(videoId)[0];
            }
            return -1;
        }
    }

/**
 * Your VideoSharingPlatform object will be instantiated and called as such:
 * VideoSharingPlatform obj = new VideoSharingPlatform();
 * int param_1 = obj.upload(video);
 * obj.remove(videoId);
 * String param_3 = obj.watch(videoId,startMinute,endMinute);
 * obj.like(videoId);
 * obj.dislike(videoId);
 * int[] param_6 = obj.getLikesAndDislikes(videoId);
 * int param_7 = obj.getViews(videoId);
 */
}
