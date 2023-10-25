package com.github.mbmll.design.pattern.creator.factory.impl;

import com.github.mbmll.design.pattern.creator.factory.Factory;
import com.github.mbmll.design.pattern.creator.factory.product.Phone;
import com.github.mbmll.design.pattern.creator.factory.product.RedmiPhoneImpl;

public class RedmiFactoryImpl implements Factory {

  @Override
  public Phone getPhone() {
    return new RedmiPhoneImpl();
  }
}
