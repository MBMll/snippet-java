package com.github.mbmll.design.pattern.factory;

import com.github.mbmll.design.pattern.creator.factory.impl.AppleAbstractFactoryImpl;
import com.github.mbmll.design.pattern.creator.factory.impl.RedmiAbstractFactoryImpl;
import com.github.mbmll.design.pattern.creator.factory.product.Charger;
import com.github.mbmll.design.pattern.creator.factory.product.Phone;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * @Author xlc
 * @Description
 * @Date 2023/10/26 00:52:38
 */

public class AbstractFactoryTest extends TestCase {

  @Test
  public void testGetPhone() {
    AppleAbstractFactoryImpl appleFactory = new AppleAbstractFactoryImpl();
    Phone applePhone = appleFactory.getPhone();
    Charger appleCharger = appleFactory.getCharger();
    System.out.println(appleFactory);
    applePhone.produce();
    appleCharger.produce();

    RedmiAbstractFactoryImpl redmiFactory = new RedmiAbstractFactoryImpl();
    Phone redmiPhone = redmiFactory.getPhone();
    Charger redmiCharger = redmiFactory.getCharger();
    System.out.println(redmiFactory);
    redmiPhone.produce();
    redmiCharger.produce();
  }

  public void testGetCharger() {
  }
}