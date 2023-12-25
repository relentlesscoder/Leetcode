package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 12/23/2023.
 * #2251 https://leetcode.com/problems/number-of-flowers-in-full-bloom/
 */
public class NumberOfFlowersInFullBloom {

    // time O(n * log(n) + m * log(m) + n * log(m)), space O(m + n)
    public int[] fullBloomFlowers(int[][] flowers, int[] people) {
        int n = people.length;
        int[] sortedPeople = new int[n];
        System.arraycopy(people, 0, sortedPeople, 0, n);
        Arrays.sort(sortedPeople);
        Arrays.sort(flowers, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        int i = 0;
        for (int person : sortedPeople) {
            while (i < flowers.length && flowers[i][0] <= person) {
                minQueue.offer(flowers[i++][1]);
            }
            while (!minQueue.isEmpty() && minQueue.peek() < person) {
                minQueue.poll();
            }
            map.put(person, minQueue.size());
        }
        for (int j = 0; j < n; j++) {
            people[j] = map.get(people[j]);
        }
        return people;
    }

    // time O(m * log(m) + n * log(m)), space O(m + n)
    public int[] fullBloomFlowersLineSweep(int[][] flowers, int[] people) {
        TreeMap<Integer, Integer> diff = new TreeMap<>();
        diff.put(0, 0);
        for (int[] flower : flowers) {
            int start = flower[0], end = flower[1] + 1;
            diff.put(start, diff.getOrDefault(start, 0) + 1);
            diff.put(end, diff.getOrDefault(end, 0) - 1);
        }
        List<Integer> positions = new ArrayList<>(), prefix = new ArrayList<>();
        int curr = 0;
        for (int key : diff.keySet()) {
            positions.add(key);
            curr += diff.get(key);
            prefix.add(curr);
        }
        int[] res = new int[people.length];
        for (int i = 0; i < people.length; i++) {
            int j = binarySearch(people[i], positions);
            res[i] = prefix.get(j);
        }
        return res;
    }

    // time O(m * log(m) + n * log(m)), space O(m + n)
    public int[] fullBloomFlowersBinarySearch(int[][] flowers, int[] people) {
        List<Integer> starts = new ArrayList<>(), ends = new ArrayList<>();
        for (int[] flower : flowers) {
            starts.add(flower[0]);
            ends.add(flower[1] + 1);
        }
        Collections.sort(starts);
        Collections.sort(ends);
        int[] res = new int[people.length];
        for (int i = 0; i < people.length; i++) {
            int j = binarySearch(people[i], starts), k = binarySearch(people[i], ends);
            res[i] = j - k;
        }
        return res;
    }

    private int binarySearch(int target, List<Integer> arr) {
        int low = 0, high = arr.size() - 1;
        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            if (arr.get(mid) > target) {
                high = mid - 1;
            } else {
                low = mid;
            }
        }
        if (low == 0 && arr.get(low) > target) {
            return -1;
        }
        return low;
    }
}
