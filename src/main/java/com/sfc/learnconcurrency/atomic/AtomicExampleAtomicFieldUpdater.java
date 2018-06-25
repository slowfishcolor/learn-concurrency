package com.sfc.learnconcurrency.atomic;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicExampleAtomicFieldUpdater {

    private static AtomicIntegerFieldUpdater<AtomicExampleAtomicFieldUpdater> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExampleAtomicFieldUpdater.class, "count");

    @Getter
    private volatile int count = 0;

    public static void main(String[] args) {
        AtomicExampleAtomicFieldUpdater example = new AtomicExampleAtomicFieldUpdater();

        updater.addAndGet(example, 100);

        if (updater.compareAndSet(example, 100, 200))
            System.out.println("success 1 " + example.getCount());

        if (updater.compareAndSet(example, 100, 200))
            System.out.println("success 2 " + example.getCount());

    }
}
