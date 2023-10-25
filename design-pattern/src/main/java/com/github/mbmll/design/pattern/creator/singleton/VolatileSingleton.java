package com.github.mbmll.design.pattern.creator.singleton;

/**
 * DCL单例 - 高性能的懒汉式
 *
 * @Author xlc
 * @Description
 * @Date 2023/10/26 01:14:14
 */

public class VolatileSingleton {

  //volatile在这里发挥的作用是：禁止指令重排序（编译器和处理器为了优化程序性能
  // 而对指令序列进行排序的一种手段。）
  // singleton = new VolatileSingleton();这句代码是非原子性操作可分为三行伪代码
  // a:memory = allocate() //分配内存，在jvm堆中分配一段区域
  // b:ctorInstanc(memory) //初始化对象，在jvm堆中的内存中实例化对象
  // c:instance = memory //赋值，设置instance指向刚分配的内存地址
  // 上面的代码在编译运行时，可能会出现重排序从a-b-c排序为a-c-b。
  // 重排序是为了优化性能，但是不管怎么重排序，在单线程下程序的执行结果不能被改变
  // 保证最终一致性。而在多线程环境下，可能发生重排序，会影响结果。
  // ①若A线程执行到代码singleton = new VolatileSingleton()时;
  // ②同时若B线程进来执行到代码到第一层检查if (singleton == null)
  // ③当cpu切换到A线程执行代码singleton = new VolatileSingleton();时发生了指令重排序，
  // 执行了a-b，没有执行c,此时的singleton对象只有地址，没有内容。然后cpu又切换到了B线程，
  // 这时singleton == null为false（==比较的是内存地址），
  // 则代码会直接执行到了return，返回一个未初始化的对象（只有地址，没有内容）。
  //
  private volatile static VolatileSingleton singleton;

  private VolatileSingleton() {
  }

  public static VolatileSingleton getSingleton() {
    //第一层检查，检查是否有引用指向对象，高并发情况下会有多个线程同时进入
    // ①当多个线程第一次进入，所有线程都进入if语句
    // ②当多个线程第二次进入，因为singleton已经不为null，因此所有线程都不会进入if语句，
    // 即不会执行锁，从而也就不会因为锁而阻塞，避免锁竞争
    if (singleton == null) {
      //第一层锁，保证只有一个线程进入，
      // ①多个线程第一次进入的时候，只有一个线程会进入，其他线程处于阻塞状态
      // 当进入的线程创建完对象出去之后，其他线程又会进入创建对象，所以有了第二次if检查
      // ②多个线程第二次是进入不到这里的，因为已被第一次if检查拦截
      synchronized (VolatileSingleton.class) {
        //第二层检查，防止除了进入的第一个线程的其他线程重复创建对象
        if (singleton == null) {
          singleton = new VolatileSingleton();
        }
      }
    }
    return singleton;
  }

}
