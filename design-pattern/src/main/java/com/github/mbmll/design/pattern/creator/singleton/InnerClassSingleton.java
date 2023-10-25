package com.github.mbmll.design.pattern.creator.singleton;

/**
 * 静态内部类的方式 <br/> ①优点：第一次类创建的时候加载，避免了内存浪费，不存在阻塞问题，线程安全，唯一性 <br/> ②缺点：序列化-漏洞：反射，会破坏内部类单例模式
 *
 * @Author xlc
 * @Description
 * @Date 2023/10/26 01:19:29
 */

public class InnerClassSingleton {

  private InnerClassSingleton() {
  }

  public static InnerClassSingleton getSingleton() {
    return LayzInner.singleton;
  }

  private static class LayzInner {

    private static InnerClassSingleton singleton = new InnerClassSingleton();
  }
}
