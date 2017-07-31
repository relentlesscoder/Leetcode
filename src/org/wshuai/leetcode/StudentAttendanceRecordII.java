package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 7/25/2017.
 * #552 https://leetcode.com/problems/student-attendance-record-ii/
 */
public class StudentAttendanceRecordII {
  static final int M = 1000000007;

  //O(n), da shen a! see https://discuss.leetcode.com/topic/86507/simple-java-o-n-solution/2
  public int checkRecord(int n) {
    if(n <= 0){
      return 0;
    }
    //count of records ending with 'P' or 'L'
    long[] pl = new long[n+1];
    //count of records ending with 'P'
    long[] p = new long[n+1];
    //length is 0 - ""
    pl[0] = p[0] = 1;
    //length is 1 - "P", "L"
    pl[1] = 2;
    //length is 1 - "L"
    p[1] = 1;

    for(int i = 2; i <= n; i++){
      //just add P to pl[i-1]
      p[i] = pl[i-1];
      //1. p[i] - ending with 'P'
      //2. p[i-1] - ending with one 'L'
      //3. p[i-2] - ending with two 'L'
      pl[i] = (p[i] + p[i-1] + p[i-2])%M;
    }

    long res = pl[n];
    for(int i = 0; i < n; i++){
      //for each position, insert an 'A'
      long s = (pl[i]*pl[n-i-1])%M;
      res = (res+s)%M;
    }

    return (int)res;
  }

  //TLE
  public int checkRecordDFS(int n) {
    if(n <= 0){
      return 0;
    }
    Set<String> set = new HashSet<String>();
    checkRecordDFSHelper("", false, set, n);
    return set.size()%M;
  }

  private void checkRecordDFSHelper(String curr, boolean absent, Set<String> set, int n){
    int len = curr.length();
    if(len == n){
      set.add(curr);
      return;
    }
    checkRecordDFSHelper(curr+"P", absent, set, n);
    if(!absent){
      checkRecordDFSHelper(curr+"A", true, set, n);
    }
    if(len <= 1 || !(curr.charAt(len-1) == 'L' && curr.charAt(len-2) == 'L')){
      checkRecordDFSHelper(curr+"L", absent, set, n);
    }
  }
}
