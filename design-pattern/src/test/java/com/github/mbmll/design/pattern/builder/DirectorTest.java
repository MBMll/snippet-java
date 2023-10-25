package com.github.mbmll.design.pattern.builder;

import com.github.mbmll.design.pattern.creator.builder.Builder;
import com.github.mbmll.design.pattern.creator.builder.ConcreteBuilder;
import com.github.mbmll.design.pattern.creator.builder.Director;
import org.junit.Test;

/**
 * @Author xlc
 * @Description
 * @Date 2023/10/26 01:32:40
 */

public class DirectorTest {

  @Test
  public void testBuild() {
    Builder builder = new ConcreteBuilder();
    Director director = new Director(builder);
    director.build();

  }
}