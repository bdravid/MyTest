package com.dravid.designpatterns.decorator;

public interface Shape {
    void draw();
    default String name(){
        return getClass().getName();
    }
}
