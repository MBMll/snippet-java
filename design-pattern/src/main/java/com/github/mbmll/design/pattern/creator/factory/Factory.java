package com.github.mbmll.design.pattern.creator.factory;

import com.github.mbmll.design.pattern.creator.factory.product.Phone;

/**
 * 工厂方法模式 <br/> 对应的构建过程较为复杂或有扩展需求时使用.
 *
 * @Author xlc
 * @Description
 * @Date 2023/10/26 00:42:03
 */

public interface Factory {

  Phone getPhone();
}

