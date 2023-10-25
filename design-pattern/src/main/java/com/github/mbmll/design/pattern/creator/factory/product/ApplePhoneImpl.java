package com.github.mbmll.design.pattern.creator.factory.product;

public class ApplePhoneImpl implements Phone {

  @Override
  public void produce() {
    System.out.println("生产苹果手机");
  }
}
