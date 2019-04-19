package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Wei on 11/14/16.
 * #341 https://leetcode.com/problems/flatten-nested-list-iterator/
 */
public class FlattenNestedListIterator implements Iterator<Integer> {
  private int pos;
  private List<Integer> list;

  public FlattenNestedListIterator(List<NestedInteger> nestedList) {
    pos = 0;
    list = new ArrayList<Integer>();
    if(nestedList != null){
      flattenList(nestedList);
    }
  }

  private void flattenList(List<NestedInteger> nestedList){
    for(NestedInteger ni: nestedList){
      if(ni.isInteger()){
        list.add(ni.getInteger());
      }else{
        flattenList(ni.getList());
      }
    }
  }

  @Override
  public Integer next() {
    return list.get(pos++);
  }

  @Override
  public boolean hasNext() {
    return pos < list.size();
  }

  @Override
  public void remove(){
    //throw new NotImplementedException();
  }
}
