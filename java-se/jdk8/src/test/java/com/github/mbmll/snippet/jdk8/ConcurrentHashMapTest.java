package com.github.mbmll.snippet.jdk8;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.Test;

/**
 * @Author xlc
 * @Description
 * @Date 2023/7/9 12:31
 */

public class ConcurrentHashMapTest {

  /**
   * 内部数组扩展
   */
  @Test
  public void expand() {
    Map<String,String> map = new ConcurrentHashMap<>();
    map.put("foo", "bar");
  }

}
