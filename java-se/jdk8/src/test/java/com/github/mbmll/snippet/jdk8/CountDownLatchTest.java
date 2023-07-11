package com.github.mbmll.snippet.jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import org.junit.Test;

/**
 * @Author xlc
 * @Description
 * @Date 2023/7/11 23:24
 */

public class CountDownLatchTest {

  @Test
  public void t() throws InterruptedException, ExecutionException {
    CountDownLatch countDownLatch = new CountDownLatch(4);
    List<CompletableFuture<Void>> x = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
        try {
          long id = Thread.currentThread().getId();
          System.out.println(id + ": " + countDownLatch.getCount());
          // 释放计数,全部计数后,释放所有的
          countDownLatch.countDown();
          System.out.println(id + ": " + countDownLatch.getCount());
          // 成功则等待,不限数量
          countDownLatch.await();
          System.out.println(id + ": " + countDownLatch.getCount());
        } catch (InterruptedException e) {
          e.printStackTrace();
          System.out.println(e.getMessage());
        }
      });
      x.add(completableFuture);
    }

    Thread.currentThread().sleep(1000L);
    countDownLatch.countDown();
    System.out.println(Thread.currentThread().getId() + ": ");
    for (CompletableFuture<Void> voidCompletableFuture : x) {
      Void unused = voidCompletableFuture.get();
    }
//    Semaphore sem = new Semaphore(3);
//    // 成功则继续
//    sem.acquire();
  }

}
