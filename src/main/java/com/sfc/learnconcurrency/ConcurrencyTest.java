package com.sfc.learnconcurrency;

import com.sfc.learnconcurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class ConcurrencyTest {

    private static final int clientTotal = 5000;

    private static final int threadTotal = 50;

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService threadPool = Executors.newFixedThreadPool(threadTotal);
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            threadPool.execute(() -> {
                try {
                    // 保证并发数
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.warn(e.toString());
                }
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        threadPool.shutdown();
        log.info("count: {}", count);


    }

    private static void add() {
        count++;
    }
}
