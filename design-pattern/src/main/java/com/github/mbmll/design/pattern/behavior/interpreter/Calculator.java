package com.github.mbmll.design.pattern.behavior.interpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.DelayQueue;

public class Calculator {

  //定义表达式
  private Expression expression;

  //构造函数传参，并解析
  public Calculator(String expStr) {
    //安排运算先后顺序
    var stack = new ArrayDeque<Expression>();
    // 表达式拆分为字符数组
    char[] charArray = expStr.toCharArray();

    Expression left = null;
    Expression right = null;
    //遍历字符数组，针对不同情况做相应处理
    for (var i = 0; i < charArray.length; i++) {
      switch (charArray[i]) {
        case '+':
          left = stack.pop(); // 从stack取出left => "a"
          right = new VarExpression(String.valueOf(charArray[++i])); // 取出右表达式 "b"
          stack.push(new AddExpression(left, right)); // 然后根据得到left 和 right 构建 AddExpresson加入stack
          break;
        case '-':
          left = stack.pop();
          right = new VarExpression(String.valueOf(charArray[++i]));
          stack.push(new SubExpression(left, right));
          break;
        default:
          //如果是一个 Var 就创建要给 VarExpression 对象，并push到 stack
          stack.push(new VarExpression(String.valueOf(charArray[i])));
          break;
      }
    }
    //当遍历完整个 charArray 数组后，stack 就得到最后Expression
    this.expression = stack.pop();
  }

  public int run(Map<String, Integer> variable) {
    //将表达式和var绑定
    return this.expression.interpreter(variable);
  }
}
