package com.github.mbmll.design.pattern.creator.builder;

public class Director {

  private Builder builder;

  public Director(Builder builder) {
    this.builder = builder;
  }

  public Product build() {
    builder.buildPart1();
    builder.buildPart2();
    builder.buildPart3();
    return builder.getResult();
  }
}
