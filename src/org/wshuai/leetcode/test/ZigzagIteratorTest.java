package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.ZigzagIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 9/27/2016.
 */
public class ZigzagIteratorTest {
  @Test
  public void testcase(){
    List<Integer> v1 = new ArrayList<Integer>();
    v1.add(1);
    v1.add(2);
    List<Integer> v2 = new ArrayList<Integer>();
    v2.add(3);
    v2.add(4);
    v2.add(5);
    v2.add(6);
    ZigzagIterator i = new ZigzagIterator(v1, v2);
    while (i.hasNext()){
      int x = i.next();
    }
  }
}
