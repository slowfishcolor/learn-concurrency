package com.sfc.learnconcurrency.book.chapter5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskExample {

    private final FutureTask<ProductInfo> futureTask = new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
        @Override
        public ProductInfo call() throws Exception {
            return loadProductInfo();
        }
    });

    private final Thread thread = new Thread(futureTask);

    public void start() {
        thread.start();
    }

    public ProductInfo get() throws ExecutionException, InterruptedException {
        return futureTask.get();
    }

    private ProductInfo loadProductInfo() throws InterruptedException {
        // do something takes time
        Thread.sleep(1000);
        return new ProductInfo();
    }

    private class ProductInfo {

    }
}
