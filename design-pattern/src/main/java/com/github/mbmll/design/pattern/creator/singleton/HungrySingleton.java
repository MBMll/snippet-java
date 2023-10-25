package com.github.mbmll.design.pattern.creator.singleton;

/**
 * 饿汉式
 *
 * @Author xlc
 * @Description
 * @Date 2023/10/26 01:07:23
 */

public class HungrySingleton {

  /**
   * static： ①表示共享变量，语意符合 ②使得该变量能在getInstance()静态方法中使用 final: ①final修饰的变量值不会改变即常量，语意也符合，当然不加final也是可以的
   * ②保证修饰的变量必须在类加载完成时就已经进行赋值。 final修饰的变量，前面一般加static
   */
  private static final HungrySingleton singleton = new HungrySingleton();

  /**
   * 私有化构造方法，使外部无法通过构造方法构造除singleton外的类实例 从而达到单例模式控制类实例数目的目的
   */
  private HungrySingleton() {
  }

  /**
   * 类实例的全局访问方法 因为构造方法以及被私有化，外部不可能通过new对象来调用其中的方法 加上static关键词使得外部可以通过类名直接调用该方法获取类实例
   *
   * @return
   */
  public static HungrySingleton getSingleton() {
    return singleton;
  }
}
