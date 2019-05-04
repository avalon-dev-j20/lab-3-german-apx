package ru.avalon.java.frames;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class AbstractFrame extends JFrame {

    private class EventListener extends WindowAdapter {

        @Override
        public void windowOpened(WindowEvent e) {
            onCreate();
        }
    }

    public AbstractFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationByPlatform(true);

        WindowAdapter listener = new EventListener();
        addWindowListener(listener);
    }

    protected void onCreate() {
    }
}