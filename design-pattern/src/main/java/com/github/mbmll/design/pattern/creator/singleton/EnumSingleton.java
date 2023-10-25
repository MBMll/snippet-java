package com.github.mbmll.design.pattern.creator.singleton;

/**
 * @Author xlc
 * @Description
 * @Date 2023/10/26 01:20:28
 */
public enum EnumSingleton {
  INSTANCE;
  private Singleton singleton;
  EnumSingleton(){
    singleton = new Singleton();
  }
  public Singleton getSingleton(){
    return singleton;
  }
}