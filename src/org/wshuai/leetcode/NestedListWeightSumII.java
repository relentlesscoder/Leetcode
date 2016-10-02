package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/1/2016.
 */
public class NestedListWeightSumII {

  public int depthSumInverse(List<NestedInteger> nestedList) {
    int sum = 0;

    if(nestedList == null || nestedList.size() == 0){
      return sum;
    }

    LinkedList<NestedInteger> curr = new LinkedList<NestedInteger>();
    Stack<Integer> sizes = new Stack<Integer>();
    List<Integer> lst = new ArrayList<Integer>();

    int csize = 0;
    int nsize = 0;
    int count = 0;

    for(NestedInteger ni: nestedList){
      if(ni.isInteger()){
        lst.add(ni.getInteger());
        count++;
      }else{
        curr.offer(ni);
        csize++;
      }
    }

    sizes.push(count);
    count = 0;
    while(csize > 0){
      NestedInteger ni = curr.poll();
      csize--;
      List<NestedInteger> nested = ni.getList();
      for(NestedInteger nip: nested){
        if(nip.isInteger()){
          lst.add(nip.getInteger());
          count++;
        }else{
          curr.offer(nip);
          nsize++;
        }
      }

      if(csize == 0){
        sizes.push(count);
        count = 0;
        csize = nsize;
        nsize = 0;
      }
    }

    int depth = 1;
    int idx = lst.size() - 1;
    while(idx >= 0){
      int countx = sizes.pop();
      while(countx > 0){
        sum += (lst.get(idx))*depth;
        countx--;
        idx--;
      }
      depth++;
    }

    return sum;
  }
}
