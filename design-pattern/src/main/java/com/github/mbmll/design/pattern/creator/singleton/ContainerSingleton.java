package com.github.mbmll.design.pattern.creator.singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author xlc
 * @Description
 * @Date 2023/10/26 01:27:27
 */

public class ContainerSingleton {

  private ContainerSingleton() {
  }

  private static Map<String, Object> ioc = new ConcurrentHashMap<>();

  public static Object getBean(String className) {
    synchronized (ioc) {
      if (ioc.containsKey(className)) {
        Object o = null;
        try {
          o = Class.forName(className).newInstance();
        } catch (Exception e) {
          e.printStackTrace();
        }
        return o;
      } else {
        return ioc.get(className);
      }
    }
  }

}
