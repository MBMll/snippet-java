package com.github.mbmll.design.pattern.creator.singleton;

/**
 * 懒汉式
 *
 * @Author xlc
 * @Description
 * @Date 2023/10/26 01:10:51
 */

public class LazySingleton {

  private static LazySingleton singleton = null;

  private LazySingleton() {
  }

  public static synchronized LazySingleton getSingleton() {
    if (singleton == null) {
      singleton = new LazySingleton();
    }
    return singleton;
  }
}
