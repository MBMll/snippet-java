package com.github.mbmll.design.pattern.behavior.interpreter;

import java.util.HashMap;
import java.util.Stack;

public class SubExpression extends SymbolExpression {

  public SubExpression(Expression left, Expression right) {
    super(left, right);
  }

  public int interpreter(HashMap<String, Integer> var) {
    //a-b,a=10,b=20
    //super.left.interpreter(var) 返回left表达式对应的值 a=10
    //super.right.interpreter(var) 返回right表达式对应的值 b=20
    return super.left.interpreter(var) - super.right.interpreter(var);
  }
}
