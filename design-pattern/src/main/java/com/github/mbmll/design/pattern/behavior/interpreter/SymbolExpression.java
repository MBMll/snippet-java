package com.github.mbmll.design.pattern.behavior.interpreter;

import java.util.HashMap;
import java.util.Map;

public class SymbolExpression implements Expression {

  protected Expression left;
  protected Expression right;

  public SymbolExpression(Expression left, Expression right) {
    this.left = left;
    this.right = right;
  }

  //SymbolExpression让其子类实现，因此interpreter是一个默认空实现
  @Override
  public int interpreter(Map<String, Integer> var) {
    return 0;
  }
}
