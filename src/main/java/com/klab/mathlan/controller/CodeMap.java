package com.klab.mathlan.controller;

public class CodeMap implements ScreenController {
    ScreenController screenController;

    public void setController(ScreenController screenController) {
        this.screenController = screenController;
    }

    @Override
    public void assignMessage(String message) {
        System.out.println(screenController.getClass().getName());
        screenController.assignMessage(message);
    }
}
