package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Wei on 02/04/2024.
 * #2502 https://leetcode.com/problems/design-memory-allocator/
 */
public class DesignMemoryAllocator {

    private class Allocator {

        private Map<Integer, Map<Integer, Integer>> allocated;
        private TreeMap<Integer, Integer> available;

        public Allocator(int n) {
            allocated = new HashMap<>();
            available = new TreeMap<>();
            available.put(0, n);
        }

        public int allocate(int size, int mID) {
            int address = -1;
            for (int offset : available.keySet()) {
                if (available.get(offset) >= size) {
                    address = offset;
                    allocated.putIfAbsent(mID, new HashMap<>());
                    allocated.get(mID).put(address, size);
                    if (available.get(offset) > size) {
                        available.put(offset + size, available.get(offset) - size);
                    }
                    available.remove(offset);
                    break;
                }
            }
            return address;
        }

        public int free(int mID) {
            int freed = 0;
            if (!allocated.containsKey(mID)) {
                return freed;
            }
            for (int address: allocated.get(mID).keySet()) {
                int size = allocated.get(mID).get(address);
                freed += size;
                int left = address, right = address + size - 1;
                while (true) { // Merge all the available blocks to the left
                    Integer offset = available.floorKey(left);
                    if (offset == null || offset + available.get(offset) < left - 1) {
                        break;
                    }
                    left = offset;
                    available.remove(offset);
                }
                while (true) { // Merge all the available blocks to the right
                    Integer offset = available.ceilingKey(right);
                    if (offset == null || offset > right + 1) {
                        break;
                    }
                    right = offset + available.get(offset) - 1;
                    available.remove(offset);
                }
                available.put(left, right - left + 1);
            }
            allocated.remove(mID);
            return freed;
        }
    }

/**
 * Your Allocator object will be instantiated and called as such:
 * Allocator obj = new Allocator(n);
 * int param_1 = obj.allocate(size,mID);
 * int param_2 = obj.free(mID);
 */
}
