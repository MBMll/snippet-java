package com.github.mbmll.design.pattern.factory;


import com.github.mbmll.design.pattern.creator.factory.SimpleFactory;
import com.github.mbmll.design.pattern.creator.factory.product.Phone;
import org.junit.Test;

/**
 * @Author xlc
 * @Description
 * @Date 2023/10/26 00:37:11
 */

class SimpleFactoryTest {

  @Test
  public void getPhone() {

    Phone redmiPhone = SimpleFactory.getPhone("红米");
    System.out.println(redmiPhone);
    redmiPhone.produce();

    Phone applePhone = SimpleFactory.getPhone("苹果");
    System.out.println(applePhone);
    applePhone.produce();
  }
}