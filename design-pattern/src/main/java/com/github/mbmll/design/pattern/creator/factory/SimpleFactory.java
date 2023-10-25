package com.github.mbmll.design.pattern.creator.factory;

import com.github.mbmll.design.pattern.creator.factory.product.ApplePhoneImpl;
import com.github.mbmll.design.pattern.creator.factory.product.Phone;
import com.github.mbmll.design.pattern.creator.factory.product.RedmiPhoneImpl;

/**
 * 简单（静态）工厂模式 <br/> 根据传入参数的数据特征,构建不同的实例.
 *
 * @Author xlc
 * @Description
 * @Date 2023/10/26 00:33:18
 */

public interface SimpleFactory {

  static Phone getPhone(String type) {
    Phone phone = null;
    if ("红米".equals(type)) {
      phone = new RedmiPhoneImpl();
    } else if ("苹果".equals(type)) {
      phone = new ApplePhoneImpl();
    }//.....
    return phone;
  }
}

