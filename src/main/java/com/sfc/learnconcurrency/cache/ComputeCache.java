package com.sfc.learnconcurrency.cache;

import java.util.concurrent.*;

public class ComputeCache<A, V> implements Computable<A, V>{

    private final ConcurrentHashMap<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> worker;

    public ComputeCache(Computable<A, V> worker) {
        this.worker = worker;
    }

    @Override
    public V compute(A arg) throws InterruptedException{
        while (true) {
            Future<V> valueFuture = cache.get(arg);
            if (valueFuture == null) {
                Callable<V> valueCallable = () -> worker.compute(arg);
                FutureTask<V> valueFutureTask = new FutureTask<>(valueCallable);
                valueFuture = cache.putIfAbsent(arg, valueFutureTask);
                if (valueFuture == null) {
                    valueFuture = valueFutureTask;
                    valueFutureTask.run();
                }
            }

            try {
                return valueFuture.get();
            } catch (CancellationException e) {
                cache.remove(arg);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
