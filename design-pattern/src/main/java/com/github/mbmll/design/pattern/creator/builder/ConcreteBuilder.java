package com.github.mbmll.design.pattern.creator.builder;

public class ConcreteBuilder extends Builder {

  @Override
  public void buildPart1() {
    System.out.println("建造part1");
  }

  @Override
  public void buildPart2() {
    System.out.println("建造part2");
  }

  @Override
  public void buildPart3() {
    System.out.println("建造part3");
  }
}
