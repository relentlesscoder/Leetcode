package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.ExclusiveTimeOfFunctions;

import java.util.ArrayList;
import java.util.List;

public class ExclusiveTimeOfFunctionsTest {
  @Test
  public void testcase(){
    ExclusiveTimeOfFunctions et = new ExclusiveTimeOfFunctions();
    List<String> input = new ArrayList<>();
    input.add("0:start:0");
    input.add("0:start:2");
    input.add("0:end:5");
    input.add("0:start:6");
    input.add("0:end:6");
    input.add("0:end:7");
    int[] res = et.exclusiveTime(2, input);
  }
}