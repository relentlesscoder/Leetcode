package org.wshuai.leetcode;

/**
 * Created by Wei on 09/01/2020.
 * #1562 https://leetcode.com/problems/find-latest-group-of-size-m/
 */
public class FindLatestGroupOfSizeM {

    // time O(n), space O(n)
    public int findLatestStep(int[] arr, int m) {
		// 维护一个数组length记录以length[i]开头或者结尾的连续1的子数组的长度和一个
		// 数组count记录长度为count[i]的连续1子数组的个数。每插入一个新的1，需要更新:
		//   1. length数组: int len = length[cur - left] = length[cur + right] = left + right + 1;
		//   2. count数组: count[left]--; count[right]--; count[len]++;
		/*
		   arr = [3,5,1,2,4]
		   initial len = [0,0,0,0,0,0,0]
		   initial cnt = [0,0,0,0,0,0]

		   Step 1:
		   a = 3, left = 0 right = 0      // int cur = arr[i], left = length[cur - 1], right = length[cur + 1];
		   len = [0,0,0,1,0,0,0]         // int len = length[cur - left] = length[cur + right] = left + right + 1;
		   cnt = [-2,1,0,0,0,0]         // count[left]--; count[right]--; count[len]++;
		   res = 1                     // if (count[m] > 0) res = i + 1;

		   Step 2:
		   a = 5, left = 0 right = 0
		   len = [0,0,0,1,0,1,0]
		   cnt = [-4,2,0,0,0,0]
		   res = 2

		   Step 3:
		   a = 1, left = 0 right = 0
		   len = [0,1,0,1,0,1,0]
		   cnt = [-6,3,0,0,0,0]
		   res = 3

		   Step 4:
		   a = 2, left = 1 right = 1
		   len = [0,3,3,3,0,1,0]
		   cnt = [-6,1,0,1,0,0]
		   res = 4

		   Step 5:
		   a = 4, left = 3 right = 1
		   len = [0,5,3,3,5,5,0]
		   cnt = [-6,0,0,0,0,1]
		   res = 4
       */
        int res = -1, n = arr.length;
        int[] length = new int[n + 2], count = new int[n + 1];
        for (int i = 0; i < n; i++) {
			int cur = arr[i], left = length[cur - 1], right = length[cur + 1];
			int len = length[cur - left] = length[cur + right] = left + right + 1;
			count[left]--;
			count[right]--;
			count[len]++;
			if (count[m] > 0) {
				res = i + 1;
			}
        }
        return res;
    }

    // time O(n), space O(n)
    public int findLatestStepUnionFind(int[] arr, int m) {
        int res = -1, n = arr.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            int idx = arr[i] - 1;
            // 把当前位置设为1
            uf.add(idx);
            // 连通左邻居如果其值为1
            if (uf.exists(idx - 1)) {
                uf.union(idx - 1, idx);
            }
            // 连通右邻居如果其值为1
            if (uf.exists(idx + 1)) {
                uf.union(idx + 1, idx);
            }
            // 判断连通集是否含有大小为m的连通分量
            if (uf.contains(m)) {
                res = i + 1;
            }
        }
        return res;
    }

    private static final class UnionFind {

        private final int n;
        private final int[] roots;
        private final int[] ranks;
        private final int[] map;

        public UnionFind(int n) {
            this.n = n;
            roots = new int[n];
            ranks = new int[n];
            map = new int[n + 1]; // map[i]为大小为i的连通分量的个数
        }

        public boolean contains(int s) {
            return map[s] > 0;
        }

        public boolean exists(int x) {
            return x >= 0 && x < n && ranks[x] != 0;
        }

        public void add(int x) {
            roots[x] = x;
            ranks[x] = 1;
            map[1]++;
        }

        public int find(int x) {
            if (x != roots[x]) {
                roots[x] = find(roots[x]);
            }
            return roots[x];
        }

        public void union(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx == ry) {
                return;
            }
            int cnt1 = ranks[rx];
            int cnt2 = ranks[ry];
            int cnt3 = cnt1 + cnt2;
            if (ranks[rx] >= ranks[ry]) {
                ranks[rx] += ranks[ry];
                roots[ry] = rx;
            } else {
                ranks[ry] += ranks[rx];
                roots[rx] = ry;
            }
            // 更新map中受此次操作影响的值
            map[cnt1]--;
            map[cnt2]--;
            map[cnt3]++;
        }
    }
}
