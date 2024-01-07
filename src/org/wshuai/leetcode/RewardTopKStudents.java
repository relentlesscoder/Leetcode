package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 01/07/2024.
 * #2512 https://leetcode.com/problems/reward-top-k-students/
 */
public class RewardTopKStudents {

    // time O(n * log(k)), space O(n)
    public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
        PriorityQueue<Student> minQueue = new PriorityQueue<>();
        Set<String> positiveSet, negativeSet;
        positiveSet = new HashSet<>(Arrays.asList(positive_feedback));
        negativeSet = new HashSet<>(Arrays.asList(negative_feedback));
        for (int i = 0; i < student_id.length; i++) {
            int score = 0;
            String[] words = report[i].split("\\s+");
            for (String w : words) {
                if (positiveSet.contains(w)) {
                    score += 3;
                } else if (negativeSet.contains(w)) {
                    score -= 1;
                }
            }
            minQueue.offer(new Student(student_id[i], score));
            if (minQueue.size() > k) {
                minQueue.poll();
            }
        }
        LinkedList<Integer> res = new LinkedList<>();
        while (!minQueue.isEmpty()) {
            res.offerFirst(minQueue.poll().id);
        }
        return res;
    }

    private static class Student implements Comparable<Student> {

        private final int id;
        private final int score;

        private Student(int id, int score) {
            this.id = id;
            this.score = score;
        }

        @Override
        public int compareTo(Student other) {
            if (this.score == other.score) {
                return Integer.compare(other.id, this.id);
            }
            return Integer.compare(this.score, other.score);
        }

    }
}
