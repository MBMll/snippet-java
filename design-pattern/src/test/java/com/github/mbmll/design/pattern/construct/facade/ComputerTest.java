package com.github.mbmll.design.pattern.construct.facade;

import org.junit.Test;

/**
 * @Author xlc
 * @Description
 * @Date 2023/10/26 01:52:26
 */

public class ComputerTest {

  @Test
  public void testStart() {
    Computer computer = new Computer();
    computer.start();
    System.out.println("=================");
    computer.shutDown();
  }

  public void testShutDown() {
  }
}