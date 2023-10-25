package com.github.mbmll.design.pattern.creator.builder;

/**
 * 建造者模式
 * <p>
 * <br/>1、建造者独立，易扩展。将复杂产品的构建过程封装分解在不同的方法中，使得创建过程非常清晰，能够让我们更加精确的控制复杂产品对象的创建过程。 <br/>
 * 2、便于控制细节风险。它隔离了复杂产品对象的创建和使用，使得相同的创建过程能够创建不同的产品。 <br/> 缺点：<br/>1、产品必须有共同点，范围有限制。 <br/> 2、如内部变化复杂，会有很多的建造类，导致系统庞大。 <br/>
 * 应用场景 <br/>1、需要生成的对象具有复杂的内部结构。<br/>2、需要生成的对象内部属性本身相互依赖。
 *
 * @Author xlc
 * @Description
 * @Date 2023/10/26 01:29:08
 */

public abstract class Builder {

  Product product = new Product();

  public abstract void buildPart1();

  public abstract void buildPart2();

  public abstract void buildPart3();

  public Product getResult() {
    return product;
  }
}

