package org.wshuai.algorithm.sorting;

import org.wshuai.utility.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Wei on 8/18/15.
 */
public class HeapSort {
	public static void sort(Comparable[] array) {
		if (array == null || array.length <= 1) {
			return;
		}

		ArrayList<Comparable> maxHeap = buildMaxHeap(array);
		for (int i = array.length - 1; i >= 0; i--) {
			array[i] = extractMax(maxHeap);
		}
	}

	private static ArrayList<Comparable> buildMaxHeap(Comparable[] array) {
		ArrayList<Comparable> maxHeap = new ArrayList<Comparable>(Arrays.asList(array));

		int length = maxHeap.size();
		for (int i = length / 2 - 1; i >= 0; i--) {
			maxHeapify(maxHeap, i);
		}
		return maxHeap;
	}

	private static void maxHeapify(ArrayList<Comparable> heap, int index) {
		int heapSize = heap.size();
		int left = 2 * index + 1;
		int right = 2 * index + 2;

		int largest = index;
		if (left < heapSize && ArrayUtil.less(heap.get(largest), heap.get(left))) {
			largest = left;
		}
		if (right < heapSize && ArrayUtil.less(heap.get(largest), heap.get(right))) {
			largest = right;
		}
		if (largest != index) {
			Comparable temp = heap.get(index);
			heap.set(index, heap.get(largest));
			heap.set(largest, temp);
			maxHeapify(heap, largest);
		}
	}

	private static Comparable extractMax(ArrayList<Comparable> heap) {
		int size = heap.size();
		if (size <= 0) {
			return null;
		}

		Comparable max = heap.get(0);
		heap.set(0, heap.get(size - 1));
		heap.remove(size - 1);
		maxHeapify(heap, 0);
		return max;
	}

	private static void increaseKey(ArrayList<Comparable> heap, int index, Comparable key) {
		if (ArrayUtil.less(key, heap.get(index))) {
			throw new UnsupportedOperationException("New key must be larger than current key.");
		}

		while (index != 0) {
			int parent;
			if (index % 2 == 0) {
				parent = index / 2 - 1;
			} else {
				parent = index / 2;
			}

			if (ArrayUtil.less(heap.get(parent), key)) {
				heap.set(index, heap.get(parent));
				heap.set(parent, key);
			}

			index = parent;
		}
	}
}
