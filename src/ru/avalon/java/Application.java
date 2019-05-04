package ru.avalon.java;

import ru.avalon.java.frames.Calculator;

import javax.swing.*;

public class Application {

    public static void main(String[] args) {

        JFrame frame = new Calculator();
        frame.setVisible(true);
    }
}