package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Wei on 01/03/2026.
 * #3508 https://leetcode.com/problems/implement-router/
 */
public class ImplementRouter {

    // time O(n * log(L)), space O(L)
    private static class Router {

        private record Packet(int source, int destination, int timestamp) {
        }

        private record Context(List<Integer> timestamps, int start) {
        }

        private final int limit;
        private final Deque<Packet> queue;
        private Set<Packet> set;
        private Map<Integer, Context> map;

        public Router(int memoryLimit) {
            limit = memoryLimit;
            queue = new ArrayDeque<>();
            set = new HashSet<>();
            map = new HashMap<>();
        }

        public boolean addPacket(int source, int destination, int timestamp) {
            Packet packet = new Packet(source, destination, timestamp);
            if (set.contains(packet)) {
                return false;
            }
            set.add(packet);
            queue.offer(packet);
            map.computeIfAbsent(destination, k -> new Context(new ArrayList<>(), 0))
                    .timestamps.add(timestamp);
            if (queue.size() > limit) {
                Packet head = queue.poll();
                Context context = map.get(head.destination);
                map.put(head.destination, new Context(context.timestamps, context.start + 1));
                set.remove(head);
            }
            return true;
        }

        public int[] forwardPacket() {
            if (queue.isEmpty()) {
                return new int[0];
            }
            Packet packet = queue.poll();
            Context context = map.get(packet.destination);
            map.put(packet.destination, new Context(context.timestamps, context.start + 1));
            set.remove(packet);
            return new int[]{packet.source, packet.destination, packet.timestamp};
        }

        public int getCount(int destination, int startTime, int endTime) {
            int res = 0;
            if (map.containsKey(destination)) {
                Context context = map.get(destination);
                int start = binarySearch(context.timestamps, context.start, startTime);
                int end = binarySearch(context.timestamps, context.start, endTime + 1);
                return end - start;
            }
            return res;
        }

        private int binarySearch(List<Integer> nums, int low, int target) {
            int high = nums.size();
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (nums.get(mid) < target) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }
    }

/**
 * Your Router object will be instantiated and called as such:
 * Router obj = new Router(memoryLimit);
 * boolean param_1 = obj.addPacket(source,destination,timestamp);
 * int[] param_2 = obj.forwardPacket();
 * int param_3 = obj.getCount(destination,startTime,endTime);
 */
}
