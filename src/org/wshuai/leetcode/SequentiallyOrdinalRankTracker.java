package org.wshuai.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by Wei on 12/23/2023.
 * #2102 https://leetcode.com/problems/sequentially-ordinal-rank-tracker/
 */
public class SequentiallyOrdinalRankTracker {

    // time O(n * log(n)), space O(n)
    private class SORTracker {

        private PriorityQueue<Location> minQueue, maxQueue;

        public SORTracker() {
            minQueue = new PriorityQueue<Location>();
            maxQueue = new PriorityQueue<Location>(Comparator.reverseOrder());
        }

        public void add(String name, int score) {
            minQueue.offer(new Location(name, score)); // try adding the new location to min queue
            maxQueue.offer(minQueue.poll()); // remove the top of the min queue and add it to the max queue (need to maintain the current min queue size since query count is unchanged)
        }

        public String get() { // maintain a min queue to store the top k locations (k = query count)
            minQueue.offer(maxQueue.poll()); // each time query is called, we remove the top from the max queue and add it to min queue
            return minQueue.peek().name;
        }

        private class Location implements Comparable<Location> {

            private String name;
            private int score;

            private Location(String name, int score) {
                this.name = name;
                this.score = score;
            }

            @Override
            public int compareTo(Location l) {
                if (this.score == l.score) {
                    return l.name.compareTo(this.name);
                }
                return this.score - l.score;
            }
        }
    }

/**
 * Your SORTracker object will be instantiated and called as such:
 * SORTracker obj = new SORTracker();
 * obj.add(name,score);
 * String param_2 = obj.get();
 */
}
