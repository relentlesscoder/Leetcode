package org.wshuai.leetcode;

/**
 * Created by Wei on 7/29/2017.   
 * #604 https://leetcode.com/problems/design-compressed-string-iterator/
 */
public class DesignCompressedStringIterator {
  private final String val;
  private char curr  = ' ';
  private int count = 0;
  private int idx = 0;

  public DesignCompressedStringIterator(String compressedString) {
    if(compressedString == null){
      this.val = "";
    }else{
      this.val = compressedString;
    }
  }

  public char next() {
    if(!hasNext()){
      return ' ';
    }
    if(count > 0){
      count--;
      return curr;
    }else if(idx < val.length()){
      int i = idx;
      curr = val.charAt(i++);
      while(i < val.length() && val.charAt(i) >= '0' && val.charAt(i) <= '9'){
        i++;
      }
      count = Integer.parseInt(val.substring(idx+1, i))-1;
      idx = i;
      return curr;
    }
    return ' ';
  }

  public boolean hasNext() {
    return !(count <= 0 && idx >= val.length());
  }
}

/**
 * Your StringIterator object will be instantiated and called as such:
 * StringIterator obj = new StringIterator(compressedString);
 * char param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
