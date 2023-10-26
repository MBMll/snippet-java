package com.github.mbmll.design.pattern.behavior.interpreter;

import java.util.HashMap;
import java.util.Map;

public class VarExpression implements Expression {

  private String key; //key=a,key=b

  public VarExpression(String key) {
    this.key = key;
  }

  //根据变量名称，返回对应的值
  @Override
  public int interpreter(Map<String, Integer> var) {
    return var.get(this.key);
  }
}
