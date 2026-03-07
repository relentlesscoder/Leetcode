package org.wshuai.leetcode.binarysearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 01/02/2026.
 * #3709 https://leetcode.com/problems/design-exam-scores-tracker/
 */
public class DesignExamScoresTracker {

    // time O(m * log(n)), space O(n)
    private static class ExamTracker {

        private final List<Integer> timestamps;
        private final List<Long> prefix;

        public ExamTracker() {
            timestamps = new ArrayList<>();
            prefix = new ArrayList<>();
            prefix.add(0L);
        }

        public void record(int time, int score) {
            timestamps.add(time);
            prefix.add(prefix.get(prefix.size() - 1) + score);
        }

        public long totalScore(int startTime, int endTime) {
            int l = lowerBound(timestamps, startTime),
                    r = higherBound(timestamps, endTime);
            if (l == -1 || r == -1) {
                return 0;
            }
            return prefix.get(r + 1) - prefix.get(l);
        }

        private int higherBound(List<Integer> nums, int target) {
            int low = 0, high = nums.size() - 1;
            while (low < high) {
                int mid = low + (high - low + 1) / 2;
                if (nums.get(mid) > target) {
                    high = mid - 1;
                } else {
                    low = mid;
                }
            }
            return nums.get(low) <= target ? low : -1;
        }

        private int lowerBound(List<Integer> nums, int target) {
            int low = 0, high = nums.size() - 1;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (nums.get(mid) < target) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return nums.get(low) >= target ? low : -1;
        }
    }

/**
 * Your ExamTracker object will be instantiated and called as such:
 * ExamTracker obj = new ExamTracker();
 * obj.record(time,score);
 * long param_2 = obj.totalScore(startTime,endTime);
 */
}
