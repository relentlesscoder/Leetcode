package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.TheEarliestMomentWhenEveryoneBecomeFriends;

public class TheEarliestMomentWhenEveryoneBecomeFriendsTest {

  @Test
  public void testcase1(){
    TheEarliestMomentWhenEveryoneBecomeFriends ebf = new TheEarliestMomentWhenEveryoneBecomeFriends();
    int[][] logs = new int[8][3];
    logs[0] = new int[]{20190101,0,1};
    logs[1] = new int[]{20190104,3,4};
    logs[2] = new int[]{20190107,2,3};
    logs[3] = new int[]{20190211,1,5};
    logs[4] = new int[]{20190224,2,4};
    logs[5] = new int[]{20190301,0,3};
    logs[6] = new int[]{20190312,1,2};
    logs[7] = new int[]{20190322,4,5};
    int res = ebf.earliestAcq(logs, 6);
  }

  @Test
  public void testcase2(){
    TheEarliestMomentWhenEveryoneBecomeFriends ebf = new TheEarliestMomentWhenEveryoneBecomeFriends();
    int[][] logs = new int[13][3];
    logs[0]  = new int[]{4,2,0};
    logs[1]  = new int[]{8,3,4};
    logs[2]  = new int[]{10,2,4};
    logs[3]  = new int[]{11,3,2};
    logs[4]  = new int[]{3,0,1};
    logs[5]  = new int[]{5,3,2};
    logs[6]  = new int[]{2,2,0};
    logs[7]  = new int[]{1,3,1};
    logs[8]  = new int[]{0,3,1};
    logs[9]  = new int[]{12,3,1};
    logs[10] = new int[]{9,3,2};
    logs[11] = new int[]{7,2,0};
    logs[12] = new int[]{6,1,0};
    int res = ebf.earliestAcq(logs, 5);
  }
}
