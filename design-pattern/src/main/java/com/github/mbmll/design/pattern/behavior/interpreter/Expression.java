package com.github.mbmll.design.pattern.behavior.interpreter;

import java.util.HashMap;
import java.util.Map;

/**
 * 解释器（Interpreter）：加强理解
 *
 * @Author xlc
 * @Description
 * @Date 2023/10/27 00:21:18
 */

public interface Expression {

  //接受公式和数值 key(表达式) value(具体值) {a=10,b=20}
  int interpreter(Map<String, Integer> variable);
}
