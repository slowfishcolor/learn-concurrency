package com.sfc.learnconcurrency.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicExampleAtomicReference {

    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0, 2); // 2
        count.compareAndSet(0, 1); // skip
        count.compareAndSet(1, 3); // skip
        count.compareAndSet(2, 4); // 4
        System.out.println(count.get());
    }
}
