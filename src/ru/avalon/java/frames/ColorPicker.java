package ru.avalon.java.frames;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class ColorPicker extends AbstractFrame {

    //инициализируем панели управления
    private final JPanel sliderPanel = new JPanel(); //панель управления движками цвета
    private final JPanel colorPanel = new JPanel(); //панель с "палитрой"

    //инициализируем цветовые движки
    private final JSlider sliderRed = new JSlider(1,0, 255, 125);
    private final JSlider sliderGreen = new JSlider(1,0, 255, 125);
    private final JSlider sliderBlue = new JSlider(1,0, 255, 125);

    //инициализируем лейблы цветовых движков
    private final JLabel labelRed = new JLabel("R");
    private final JLabel labelGreen = new JLabel("G");
    private final JLabel labelBlue = new JLabel("B");

    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    Clipboard clipboard = toolkit.getSystemClipboard();

    @Override
    protected void onCreate() {

        setTitle("Color Picker"); //заголовок
        setSize(800, 400); //размер окна
        setLayout(new GridLayout(1, 2)); //первичная разметка окна

        add(createColorPanel()); //вывод палитры
        add(createSliderPanel()); //вывод панели управления

        //отслеживание изменения положения движков
        sliderRed.addChangeListener(this::onSliderChange);
        sliderGreen.addChangeListener(this::onSliderChange);
        sliderBlue.addChangeListener(this::onSliderChange);

        //обновление цвета палитры при запуске,
        //иначе 0,0,0 и черная палитра
        updateColor();
    }

    private JPanel createColorPanel() {

        colorPanel.setLayout(new BorderLayout());
        colorPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));

        //цвет палитры на основе положения движков ползунков
        colorPanel.setBackground(new Color(sliderRed.getX(), sliderGreen.getX(), sliderBlue.getX()));

        return colorPanel;
    }

    private JPanel createSliderPanel() {

        sliderPanel.setLayout(new GridLayout(1, 3)); //разметка панели управления
        sliderPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        sliderPanel.setBackground(Color.GRAY);

        //создание и оформление зон будущих элементов управления
        JPanel panelRed = new JPanel(new GridLayout(1, 1));
        JPanel panelGreen = new JPanel(new GridLayout(1, 1));
        JPanel panelBlue = new JPanel(new GridLayout(1, 1));

        panelRed.setBackground(Color.GRAY);
        panelGreen.setBackground(Color.GRAY);
        panelBlue.setBackground(Color.GRAY);

        sliderPanel.add(panelRed);
        sliderPanel.add(panelGreen);
        sliderPanel.add(panelBlue);

        panelRed.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
        panelGreen.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
        panelBlue.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //добавление лейблов и слайдеров в элементы управления
        panelRed.add(labelRed);
        panelRed.add(sliderRed);
        panelGreen.add(labelGreen);
        panelGreen.add(sliderGreen);
        panelBlue.add(labelBlue);
        panelBlue.add(sliderBlue);

        //настройка слайдеров управления
        sliderRed.setBackground(Color.GRAY);
        sliderRed.setPaintLabels(true);
        sliderRed.setMajorTickSpacing(255);

        sliderGreen.setBackground(Color.GRAY);
        sliderGreen.setPaintLabels(true);
        sliderGreen.setMajorTickSpacing(255);

        sliderBlue.setBackground(Color.GRAY);
        sliderBlue.setPaintLabels(true);
        sliderBlue.setMajorTickSpacing(255);

        return sliderPanel;
    }

    private void copyToClipboard(String text) {
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, selection);
    }

    private void onSliderChange(ChangeEvent e) {
        updateColor();
    }

    private void updateColor() {
        colorPanel.setBackground(new Color(sliderRed.getValue(), sliderGreen.getValue(), sliderBlue.getValue()));
        String colorBack = String.format("#%02X%02X%02X", sliderRed.getValue(), sliderGreen.getValue(), sliderBlue.getValue());
        colorPanel.setToolTipText(colorBack);
        copyToClipboard(colorBack);
    }
}