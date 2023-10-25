package com.github.mbmll.design.pattern.creator.singleton;

/**
 * @Author xlc
 * @Description
 * @Date 2023/10/26 01:09:41
 */

public class HungrySingleton2 {

  private static final HungrySingleton2 singletonStatic;

  /**
   * 和第一种没有什么区别，这种看起来高大上面试装逼使用
   */
  static {
    singletonStatic = new HungrySingleton2();
  }

  private HungrySingleton2() {}

  public static HungrySingleton2 getHungrySingleton2(){
    return singletonStatic;
  }
}
