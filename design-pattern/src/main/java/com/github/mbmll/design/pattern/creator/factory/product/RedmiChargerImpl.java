package com.github.mbmll.design.pattern.creator.factory.product;

public class RedmiChargerImpl implements Charger {

  @Override
  public void produce() {
    System.out.println("生产红米充电器");
  }
}
