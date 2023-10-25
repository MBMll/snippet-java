package com.github.mbmll.design.pattern.creator.factory.impl;

import com.github.mbmll.design.pattern.creator.factory.Factory;
import com.github.mbmll.design.pattern.creator.factory.product.ApplePhoneImpl;
import com.github.mbmll.design.pattern.creator.factory.product.Phone;

public class AppleFactoryImpl implements Factory {

  @Override
  public Phone getPhone() {
    return new ApplePhoneImpl();
  }
}
