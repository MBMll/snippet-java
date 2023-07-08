package com.github.mbmll.snippet.jdk8;


import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.junit.Test;

/**
 * @Author xlc
 * @Description
 * @Date 2023/7/8 23:27
 */

class CopyOnWriteArrayListTest {

  /**
   * {@link CopyOnWriteArrayList} 把处理中间问题的步骤丢到 write 下, 使 read 操作尽可能的快.
   */
  @Test
  public void t() {
    List<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
    copyOnWriteArrayList.add("sdd");
    copyOnWriteArrayList.iterator().next();
    copyOnWriteArrayList.get(0);
  }
}