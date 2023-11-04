package com.github.mbmll.design.pattern.behavior.state;

public class Player {

  State placeA;
  State placeB;
  State onMove;
  private State state;
  private String direction;

  public Player() {
    direction = "AB";
    placeA = new PlaceA(this);
    placeB = new PlaceB(this);
//    onMove = new OnMove(this);
    this.state = placeA;
  }

  public void move() {
    System.out.println("指令:开始移动");
    state.move();
  }

  public void stop() {
    System.out.println("指令:停止移动");
    state.stop();
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  public String getDirection() {
    return direction;
  }

}
