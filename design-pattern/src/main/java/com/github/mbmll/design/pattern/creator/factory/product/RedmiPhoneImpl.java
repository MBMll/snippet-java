package com.github.mbmll.design.pattern.creator.factory.product;

public class RedmiPhoneImpl implements Phone {

  @Override
  public void produce() {
    System.out.println("生产了红米手机");
  }
}
