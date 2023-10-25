package com.github.mbmll.design.pattern.creator.factory.product;

public class AppleChargerImpl implements Charger {

  @Override
  public void produce() {
    System.out.println("生产苹果充电器");
  }
}
