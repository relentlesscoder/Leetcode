package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Wei on 11/4/16.
 * #373 https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
 */
public class FindKPairsWithSmallestSums {
  public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k)
  {
    List<int[]> lst = new ArrayList<int[]>();
    if(nums1 == null || nums2 == null){
      return lst;
    }
    int len1 = nums1.length;
    int len2 = nums2.length;
    int cnt = Math.min(len1*len2, k);
    if(cnt == 0){
      return lst;
    }
    int[] aux1 = new int[]{0, 1};
    int[] aux2 = new int[]{1, 0};
    boolean[][] vis = new boolean[len1][len2];
    PriorityQueue<PairSumObj> pq = new PriorityQueue<PairSumObj>(new PairSumComparator());
    pq.offer(new PairSumObj(0, 0, nums1[0]+nums2[0]));
    vis[0][0] = true;
    while(cnt > 0){
      PairSumObj ps = pq.poll();
      int[] arr = new int[2];
      arr[0] = nums1[ps.x];
      arr[1] = nums2[ps.y];
      lst.add(arr);
      for(int i = 0; i < 2; i++){
        int x1 = ps.x + aux1[i];
        int y1 = ps.y + aux2[i];
        if(x1 < len1 && y1 < len2 && !vis[x1][y1]){
          PairSumObj nxt = new PairSumObj(x1, y1, nums1[x1]+nums2[y1]);
          pq.offer(nxt);
          vis[x1][y1] = true;
        }
      }

      cnt--;
    }

    return lst;
  }
}

class PairSumObj{
  int x;
  int y;
  int sum;
  public PairSumObj(int x, int y, int sum){
    this.x = x;
    this.y = y;
    this.sum = sum;
  }
}

class PairSumComparator implements Comparator<PairSumObj> {
  @Override
  public int compare(PairSumObj ps1, PairSumObj ps2){
    return ps1.sum - ps2.sum;
  }
}
