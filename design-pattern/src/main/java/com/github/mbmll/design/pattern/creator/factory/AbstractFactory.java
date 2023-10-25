package com.github.mbmll.design.pattern.creator.factory;

import com.github.mbmll.design.pattern.creator.factory.product.Charger;
import com.github.mbmll.design.pattern.creator.factory.product.Phone;

/**
 * 抽象工厂模式
 *
 * @Author xlc
 * @Description
 * @Date 2023/10/26 00:48:42
 */

public interface AbstractFactory {

  Phone getPhone();

  Charger getCharger();

}

