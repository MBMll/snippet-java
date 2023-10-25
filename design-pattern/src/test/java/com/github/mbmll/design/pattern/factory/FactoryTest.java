package com.github.mbmll.design.pattern.factory;

import com.github.mbmll.design.pattern.creator.factory.Factory;
import com.github.mbmll.design.pattern.creator.factory.impl.AppleFactoryImpl;
import com.github.mbmll.design.pattern.creator.factory.impl.RedmiFactoryImpl;
import com.github.mbmll.design.pattern.creator.factory.product.Phone;
import org.junit.Test;

/**
 * @Author xlc
 * @Description
 * @Date 2023/10/26 00:47:17
 */

public class FactoryTest {

  @Test
  public void testGetPhone() {
    Factory applePhoneFactory = new AppleFactoryImpl();
    Factory redmiPhoneFactory = new RedmiFactoryImpl();

    Phone applePhone = applePhoneFactory.getPhone();
    Phone redmiPhone = redmiPhoneFactory.getPhone();

    System.out.println(applePhone);
    System.out.println(redmiPhone);

    applePhone.produce();
    redmiPhone.produce();
  }
}