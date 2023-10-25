package com.github.mbmll.design.pattern.creator.prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * 原型模式 <br/> 通过复制现有的实例来创建新的实例。因为是同过原有的对象创建新的对象，所以称为原型模式。
 *
 * @Author xlc
 * @Description
 * @Date 2023/10/26 00:08:10
 */

public class Prototype implements Cloneable{

  private Integer id;
  private String name;
  private Map<String, Double> map;

  @Override
  protected Prototype clone() throws CloneNotSupportedException {
    //浅拷贝方式
    Prototype prototype = (Prototype) super.clone();
    //深拷贝方式：对每一个复杂类型分别进行克隆
    //测试浅拷贝的时候注释下面代码
    prototype.map = (Map<String, Double>) ((HashMap<?, ?>)this.map).clone();
    return prototype;
  }
}
