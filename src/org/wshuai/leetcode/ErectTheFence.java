package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 1/2/2020.
 * #587 https://leetcode.com/problems/erect-the-fence/
 */
public class ErectTheFence {
	// https://www.geeksforgeeks.org/convex-hull-set-1-jarviss-algorithm-or-wrapping/
	// time complexity O(n*h)
	public int[][] outerTreesJarvis(int[][] points) {
		int n = points.length;
		if(n < 4){
			return points;
		}
		List<int[]> list = new ArrayList<>();
		int l = 0, p = 0, q = 0;
		for(int i = 0; i < n; i++){
			if(points[i][0] < points[l][0]){
				l = i;
			}
		}
		p = l;
		do{
			list.add(points[p]);
			q = (p + 1) % n;

			for(int i = 0; i < n; i++){
				if(orient(points[p], points[i], points[q]) == 2){
					q = i;
				}
			}

			for(int i = 0; i < n; i++){
				if(i == p || i == q){
					continue;
				}
				if(orient(points[p], points[i], points[q]) == 0 && onSeg(points[p], points[i], points[q])){
					list.add(points[i]);
				}
			}

			p = q;
		}while(p != l);
		int[][] res = new int[list.size()][2];
		int k = 0;
		for(int[] t : list){
			res[k++] = t;
		}
		return res;
	}

	// https://www.geeksforgeeks.org/check-if-two-given-line-segments-intersect/
	private int orient(int[] p, int[] q, int[] r){
		int v = (q[1] - p[1]) * (r[0] - q[0]) - (r[1] - q[1]) * (q[0] - p[0]);
		if(v == 0){
			return 0;
		}
		return v > 0 ? 1 : 2;
	}

	// https://www.geeksforgeeks.org/check-if-two-given-line-segments-intersect/
	private boolean onSeg(int[] p, int[] r, int[] q){
		return r[0] <= Math.max(p[0], q[0])
				&& r[0] >= Math.min(p[0], q[0])
				&& r[1] <= Math.max(p[1], q[1])
				&& r[1] >= Math.min(p[1], q[1]);
	}

	// https://leetcode.com/problems/erect-the-fence/discuss/181735/Two-scan-Graham-java-solution.
	public int[][] outerTreesGrahamScan(int[][] points) {
		if (points.length == 1) {
			return points;
		}
		int n = points.length;
		Arrays.sort(points,(a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
		HashSet<ArrayList<Integer>> dup = new HashSet();
		List<int[]> list = new ArrayList<>();
		Stack<int[]> hull = new Stack<>();
		hull.push(points[0]);
		hull.push(points[1]);
		// Graham scan;
		for (int i = 2; i < n; i++) {
			int[] top = hull.pop();
			while (!hull.isEmpty() && ccw(hull.peek(), top, points[i]) < 0) {
				top = hull.pop();
			}
			hull.push(top);
			hull.push(points[i]);
		}
		for(int i = n-2;i >= 0;i--) {
			int[] top = hull.pop();
			while (!hull.isEmpty() && ccw(hull.peek(), top, points[i]) < 0) {
				top = hull.pop();
			}
			hull.push(top);
			hull.push(points[i]);
		}
		for(int[] x: hull) {
			ArrayList<Integer> tmp = new ArrayList<>();
			tmp.add(x[0]);
			tmp.add(x[1]);
			if(dup.contains(tmp)) continue;
			dup.add(tmp);
			list.add(x);
		}
		int[][] res = new int[list.size()][2];
		int k = 0;
		for(int[] t : list){
			res[k++] = t;
		}
		return res;
	}

	public int ccw(int[] a, int[] b, int[] c) {
		double area2 = (b[0] - a[0]) * (c[1] - a[1]) - (b[1] - a[1]) * (c[0] - a[0]);
		if      (area2 < 0) return -1;
		else if (area2 > 0) return +1;
		else                return  0;
	}
}
