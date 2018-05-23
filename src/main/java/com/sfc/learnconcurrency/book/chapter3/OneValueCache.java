package com.sfc.learnconcurrency.book.chapter3;

import com.sfc.learnconcurrency.annotations.Immutable;

import java.math.BigInteger;
import java.util.Arrays;

@Immutable
public class OneValueCache {

    private final BigInteger lastNumber;

    private final BigInteger[] lastFactors;

    public OneValueCache(BigInteger i, BigInteger[] factors) {
        lastNumber = i;
        // 防止逸出
        lastFactors = Arrays.copyOf(factors, factors.length);
    }

    public BigInteger[] getFactors(BigInteger i) {
        if (lastNumber == null || !lastNumber.equals(i)) {
            return null;
        }
        // 返回一个不可变对象，防止逸出
        return Arrays.copyOf(lastFactors, lastFactors.length);
    }
}
