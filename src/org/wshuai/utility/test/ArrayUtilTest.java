package org.wshuai.utility.test;

import org.junit.Test;
import org.wshuai.utility.ArrayUtil;

/**
 * Created by Wei on 8/23/15.
 */
public class ArrayUtilTest {
  @Test(expected = NullPointerException.class)
  public void isSortedThrowsExceptionWhenInputIsNull(){
    ArrayUtil.isSorted(null);
  }
}
