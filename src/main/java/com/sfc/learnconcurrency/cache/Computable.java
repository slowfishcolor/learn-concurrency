package com.sfc.learnconcurrency.cache;

public interface Computable<A, V> {

    V compute(A arg) throws InterruptedException;
}
