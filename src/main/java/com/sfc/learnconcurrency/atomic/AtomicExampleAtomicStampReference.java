package com.sfc.learnconcurrency.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 解决 CAS 的 ABA 问题
 * 通过增加版本号 stamp
 */
public class AtomicExampleAtomicStampReference {

    public static void main(String[] args) {
        AtomicStampedReference<Integer> money = new AtomicStampedReference<Integer>(19, 0);
    }
}
