package com.github.mbmll.snippet.jdk8;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import org.junit.Test;

/**
 * @Author xlc
 * @Description
 * @Date 2023/7/11 22:47
 */

public class ReentrantLockTest {

  @Test
  public void t() throws InterruptedException {
    ReentrantLock lock = new ReentrantLock();
    lock.lock();
    lock.lock();
    System.out.println(lock.getHoldCount());
    lock.unlock();
    lock.unlock();
    System.out.println(lock.getHoldCount());
  }
}
