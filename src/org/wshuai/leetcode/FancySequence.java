package org.wshuai.leetcode;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/23/2020.
 * #1622 https://leetcode.com/problems/fancy-sequence/
 */
public class FancySequence {

    public static final BigInteger MOD = BigInteger.valueOf((int)(1e9 + 7));
    private List<BigInteger> ans = new ArrayList<>();
    private List<BigInteger> mult = new ArrayList<>();
    private List<BigInteger> added = new ArrayList<>();

    public FancySequence() {
        mult.add(BigInteger.ONE);
        added.add(BigInteger.ZERO);
    }

    public void append(int val) {
        ans.add(BigInteger.valueOf(val));
        mult.add(mult.get(mult.size() - 1));
        added.add(added.get(added.size() - 1));
    }

    public void addAll(int inc) {
        BigInteger last = added.get(added.size() - 1);
        added.remove(added.size() - 1);
        added.add(last.add(BigInteger.valueOf(inc)).mod(MOD));
    }

    public void multAll(int m) {
        BigInteger lastAdded = added.get(added.size() - 1);
        BigInteger lastMultiplied = mult.get(mult.size() - 1);
        added.remove(added.size() - 1);
        added.add(lastAdded.multiply(BigInteger.valueOf(m)).mod(MOD));
        mult.remove(mult.size() - 1);
        mult.add(lastMultiplied.multiply(BigInteger.valueOf(m)).mod(MOD));
    }

    public int getIndex(int idx) {
        if (idx >= ans.size()) {
            return -1;
        }
        BigInteger quotient = mult.get(mult.size() - 1);
        BigInteger dividend = mult.get(idx);
        BigInteger factor = quotient.multiply(dividend.modInverse(MOD)).mod(MOD);
        BigInteger inc = added.get(added.size() - 1).subtract(added.get(idx).multiply(factor).mod(MOD));
        return ans.get(idx).multiply(factor).mod(MOD).add(inc).mod(MOD).intValue();
    }
}
