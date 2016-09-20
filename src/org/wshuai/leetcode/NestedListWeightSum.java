package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 9/18/2016.
 */
public class NestedListWeightSum {
  public int depthSum(List<NestedInteger> nestedList) {
    return getDepthSum(nestedList, 1);
  }

  public int getDepthSum(List<NestedInteger> nestedList, int depth){
    if(nestedList == null || nestedList.size() == 0){
      return 0;
    }
    int sum = 0;
    int len = nestedList.size();
    for(int i = 0; i < len; i++){
      NestedInteger ni = nestedList.get(i);
      if(ni.isInteger()){
        sum += ni.getInteger()*depth;
      }else{
        sum += getDepthSum(ni.getList(), depth + 1);
      }
    }
    return sum;
  }
}
