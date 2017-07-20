package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 7/6/2017.
 * #282 https://leetcode.com/problems/expression-add-operators/
 */
public class ExpressionAddOperators {
  // See https://discuss.leetcode.com/topic/24523/java-standard-backtrace-ac-solutoin-short-and-clear
  public List<String> addOperators(String num, int target) {
    List<String> res = new ArrayList<String>();
    if(num == null || num.isEmpty()){
      return res;
    }
    addOperatorsUtil(res, "", num, target, 0, 0, 0);
    return res;
  }

  private void addOperatorsUtil(List<String> res, String path, String num, int target, int pos, long eval, long multed){
    if(pos == num.length()){
      if(target == eval){
        res.add(path);
      }
      return;
    }
    for(int i = pos; i < num.length(); i++){
      // excludes invalid integer expression such as "0004"
      if(i != pos && num.charAt(pos) == '0'){
        break;
      }
      long cur = Long.parseLong(num.substring(pos, i+1));
      if(pos == 0){
        addOperatorsUtil(res, path+cur, num, target, i+1, cur, cur);
      }else{
        addOperatorsUtil(res, path+"+"+cur, num, target, i+1, eval+cur, cur);
        addOperatorsUtil(res, path+"-"+cur, num, target, i+1, eval-cur, -cur);
        //Two examples:
        // 50(45+5)-5+5*4
        // 50(30+5*4)-5*4+5*4*3
        addOperatorsUtil(res, path+"*"+cur, num, target, i+1, eval-multed+multed*cur, multed*cur);
      }
    }
  }
}
