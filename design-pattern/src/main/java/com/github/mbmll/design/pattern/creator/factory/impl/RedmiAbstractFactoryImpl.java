package com.github.mbmll.design.pattern.creator.factory.impl;

import com.github.mbmll.design.pattern.creator.factory.AbstractFactory;
import com.github.mbmll.design.pattern.creator.factory.product.Charger;
import com.github.mbmll.design.pattern.creator.factory.product.Phone;
import com.github.mbmll.design.pattern.creator.factory.product.RedmiChargerImpl;
import com.github.mbmll.design.pattern.creator.factory.product.RedmiPhoneImpl;

public class RedmiAbstractFactoryImpl implements AbstractFactory {

  @Override
  public Phone getPhone() {
    return new RedmiPhoneImpl();
  }

  @Override
  public Charger getCharger() {
    return new RedmiChargerImpl();
  }
}
