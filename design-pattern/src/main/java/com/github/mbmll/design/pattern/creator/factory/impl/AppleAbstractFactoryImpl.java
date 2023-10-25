package com.github.mbmll.design.pattern.creator.factory.impl;

import com.github.mbmll.design.pattern.creator.factory.AbstractFactory;
import com.github.mbmll.design.pattern.creator.factory.product.AppleChargerImpl;
import com.github.mbmll.design.pattern.creator.factory.product.ApplePhoneImpl;
import com.github.mbmll.design.pattern.creator.factory.product.Charger;
import com.github.mbmll.design.pattern.creator.factory.product.Phone;

public class AppleAbstractFactoryImpl implements AbstractFactory {

  @Override
  public Phone getPhone() {
    return new ApplePhoneImpl();
  }

  @Override
  public Charger getCharger() {
    return new AppleChargerImpl();
  }
}
