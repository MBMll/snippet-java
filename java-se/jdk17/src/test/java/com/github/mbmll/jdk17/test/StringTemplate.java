package com.github.mbmll.jdk17.test;

import org.junit.Test;

/**
 * @Author xlc
 * @Description
 * @Date 2023/9/20 21:58:59
 */

public class StringTemplate {

  @Test
  public void testStringTemplate() {
    var x =
      """
        select
          *
        from abc
      """;
    System.out.println(x);
  }
}
