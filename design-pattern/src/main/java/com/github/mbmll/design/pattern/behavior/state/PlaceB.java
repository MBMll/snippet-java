package com.github.mbmll.design.pattern.behavior.state;

public class PlaceB implements State {

  private Player context;

  public PlaceB(Player context) {
    this.context = context;
  }

  @Override
  public void move() {
    System.out.println("处于地点A,开始向B移动");
    System.out.println("--------");
    context.setDirection("AB");
    context.setState(context.onMove);

  }

  @Override
  public void stop() {
    // TODO Auto-generated method stub
    System.out.println("正处在地点A，不用停止移动");
    System.out.println("--------");
  }

}
